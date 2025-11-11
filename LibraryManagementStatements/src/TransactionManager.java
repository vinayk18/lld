import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionManager {

    private final AtomicInteger nextTxId = new AtomicInteger(1);

    // master storage
    private final Map<Integer, Transaction> txById = new HashMap<>();

    // active indices
    private final Map<Integer, Transaction> activeByCopy = new HashMap<>();        // copyId -> active tx
    private final Map<Integer, List<Transaction>> txByUser = new HashMap<>();     // userId -> all tx (active + history)

    private final InventoryManager inventoryManager;
    private final BookCatalog bookCatalog;
    private final UserManager userManager;
    private final PaymentCalculator paymentCalculator;

    // loan policy: default 14 days; you could hook membership type here
    private final int defaultLoanDays;

    public TransactionManager(InventoryManager inventoryManager,
                              BookCatalog bookCatalog,
                              UserManager userManager,
                              PaymentCalculator paymentCalculator,
                              int defaultLoanDays) {
        this.inventoryManager = inventoryManager;
        this.bookCatalog = bookCatalog;
        this.userManager = userManager;
        this.paymentCalculator = paymentCalculator == null ? new PaymentCalculator() : paymentCalculator;
        this.defaultLoanDays = defaultLoanDays <= 0 ? 14 : defaultLoanDays;
    }

    public TransactionManager(InventoryManager inventoryManager,
                              BookCatalog bookCatalog,
                              UserManager userManager) {
        this(inventoryManager, bookCatalog, userManager, new PaymentCalculator(), 14);
    }

    /**
     * Issue a book to a user. Orchestrates allocations and creates a Transaction.
     * Throws RuntimeException (BusinessException replacement) for business failures.
     */
    public synchronized Transaction issueBook(int userId, int bookId) {
        // 1. user check
        User user = userManager.getUserById(userId);
        if (user == null) throw new RuntimeException("User not found: " + userId);

        // (Optional) enforce membership limits or unpaid fines here:
        // if (user.getFinesDue() > threshold) throw ...

        // 2. book check
        Book book = bookCatalog.getBook(bookId);
        if (book == null || book.getStatus() != BOOK_STATUS.ACTIVE)
            throw new RuntimeException("Book not available in catalog: " + bookId);

        // 3. find available copy
        Optional<PhysicalBook> opt = inventoryManager.getAnyAvailableCopy(bookId);
        if (opt.isEmpty()) throw new RuntimeException("No available copies for bookId: " + bookId);

        PhysicalBook copy = opt.get();

        // 4. allocate copy atomically
        boolean allocated = inventoryManager.allocateCopy(copy.getCopyId());
        if (!allocated) throw new RuntimeException("Failed to allocate copy: " + copy.getCopyId());

        // 5. create transaction
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime due = now.plusDays(defaultLoanDays);
        int txId = nextTxId.getAndIncrement();
        Transaction tx = new Transaction(txId, userId, copy.getCopyId(), bookId, now, due);

        // 6. persist in-memory and indices
        txById.put(txId, tx);
        activeByCopy.put(copy.getCopyId(), tx);
        txByUser.computeIfAbsent(userId, k -> new ArrayList<>()).add(tx);

        // 7. update user view (convenience). Assumes User has borrowBook(Book)
        user.borrowBook(book);

        return tx;
    }

    /**
     * Return a copy. Finds the active transaction for the copy, closes it, computes fine,
     * marks inventory returned and updates user.
     */
    public synchronized Transaction returnBook(int copyId) {
        Transaction tx = activeByCopy.get(copyId);
        if (tx == null) throw new RuntimeException("No active transaction for copyId: " + copyId);

        LocalDateTime now = LocalDateTime.now();

        // compute fine
        double fine = paymentCalculator.calculateFine(tx.getDueDate(), now);

        // update transaction
        tx.setReturnDate(now);
        tx.setFineAmount(fine);
        tx.setStatus(TransactionStatus.RETURNED);

        // persist updates: remove from activeByCopy
        activeByCopy.remove(copyId);
        // tx remains in txByUser and txById for history

        // mark copy returned in inventory
        boolean returned = inventoryManager.returnCopy(copyId);
        if (!returned) {
            // unexpected; best-effort: keep transaction closed, but log out-of-band
        }

        // update user: assume userManager has getUserById and user.returnBook
        User user = userManager.getUserById(tx.getUserId());
        if (user != null) {
            user.returnBook(bookCatalog.getBook(tx.getBookId())); // relies on Book equality in User.returnBook
        }

        // if fine > 0, record it on user
        if (fine > 0) {
            // userManager.addFine(tx.getUserId(), fine); // if available
        }

        return tx;
    }

    public synchronized Optional<Transaction> getActiveTransactionByCopy(int copyId) {
        return Optional.ofNullable(activeByCopy.get(copyId));
    }

    public synchronized List<Transaction> getActiveTransactionsForUser(int userId) {
        List<Transaction> all = txByUser.getOrDefault(userId, Collections.emptyList());
        List<Transaction> active = new ArrayList<>();
        for (Transaction t : all) {
            if (t.getStatus() == TransactionStatus.ISSUED || t.getStatus() == TransactionStatus.OVERDUE) {
                active.add(t);
            }
        }
        return active;
    }

    public synchronized List<Transaction> getTransactionHistoryForUser(int userId) {
        return new ArrayList<>(txByUser.getOrDefault(userId, Collections.emptyList()));
    }

    public synchronized List<Transaction> getOverdueTransactions() {
        LocalDateTime now = LocalDateTime.now();
        List<Transaction> overdue = new ArrayList<>();
        for (Transaction t : txById.values()) {
            if (t.getStatus() == TransactionStatus.ISSUED && t.getDueDate().isBefore(now)) {
                overdue.add(t);
                // optionally mark as OVERDUE flag
                // t.setStatus(TransactionStatus.OVERDUE);
            }
        }
        return overdue;
    }

    /**
     * Mark a copy as lost. Close the active transaction (if exists) as LOST and charge replacement.
     */
    public synchronized void markLost(int copyId, double replacementCost) {
        Transaction tx = activeByCopy.get(copyId);
        if (tx != null) {
            tx.setReturnDate(LocalDateTime.now());
            tx.setFineAmount(replacementCost);
            tx.setStatus(TransactionStatus.LOST);
            activeByCopy.remove(copyId);
        }
        // also mark the physical copy as LOST in inventory if you have that state
        PhysicalBook p = inventoryManager.findCopy(copyId); // if method exists; adapt otherwise
        if (p != null) p.setStatus(COPY_STATUS.LOST);
    }
}