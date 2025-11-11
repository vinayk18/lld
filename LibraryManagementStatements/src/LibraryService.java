import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LibraryService {

    private final InventoryManager inventoryManager;
    private final BookCatalog bookCatalog;
    private final UserManager userManager;
    private final TransactionManager transactionManager;

    public LibraryService() {
        this.bookCatalog = new BookCatalog();
        this.inventoryManager = new InventoryManager();
        this.userManager = new UserManager();
        this.transactionManager = new TransactionManager(inventoryManager, bookCatalog, userManager);
    }

    // Constructor for dependency injection (useful for tests)
    public LibraryService(BookCatalog catalog,
                          InventoryManager inventory,
                          UserManager users,
                          TransactionManager txManager) {
        this.bookCatalog = catalog;
        this.inventoryManager = inventory;
        this.userManager = users;
        this.transactionManager = txManager;
    }

    // Admin operation: add a title (if missing) and create copies
    public synchronized List<PhysicalBook> addBooks(String name, String author, int edition, int copies) {
        if (copies <= 0) return Collections.emptyList();

        // Try to find existing catalog entry
        Book book = bookCatalog.findBook(name, author, edition);
        if (book == null) {
            book = new Book(name, author, edition);
            bookCatalog.addBook(book);
        }

        // Create physical copies in inventory and return them
        return inventoryManager.addCopies(book, copies);
    }

    // Admin operation: remove up to 'count' available copies for the title
    public synchronized List<PhysicalBook> removeBooks(String name, String author, int edition, int count) {
        if (count <= 0) return Collections.emptyList();

        Book book = bookCatalog.findBook(name, author, edition);
        if (book == null) return Collections.emptyList();

        return inventoryManager.removeCopies(book, count);
    }

    // User management
    public synchronized void addUser(String name) {
        userManager.addUser(name);
    }

    public synchronized boolean removeUser(int userId) {
        User user = userManager.getUserById(userId);
        if (user == null) return false;

        // refuse removal if user still has borrowed books
        if (!user.getBorrowedBooks().isEmpty()) return false;

        userManager.removeUser(user);
        return true;
    }

    // --- Lending operations (core) ---

    /**
     * Issue a book to a user by title metadata.
     * Returns the created Transaction on success.
     */
    public synchronized Transaction issueBook(int userId, String name, String author, int edition) {
        // 1) Validate user
        User user = userManager.getUserById(userId);
        if (user == null) throw new RuntimeException("User not found: " + userId);

        // 2) Find book in catalog
        Book book = bookCatalog.findBook(name, author, edition);
        if (book == null) throw new RuntimeException("Book not found in catalog: " + name);

        if (book.getStatus() != BOOK_STATUS.ACTIVE) {
            throw new RuntimeException("Book is not active for issuing: " + book.getBookId());
        }

        // 3) Delegate to TransactionManager which orchestrates allocation + transaction creation
        Transaction tx = transactionManager.issueBook(userId, book.getBookId());

        // transactionManager already updated user and inventory in its flow (as designed)
        return tx;
    }

    /**
     * Return a copy by copyId.
     * Returns the updated Transaction (with fines computed, status = RETURNED).
     */
    public synchronized Transaction returnBook(int copyId) {
        // Delegate to transaction manager which closes transaction and updates inventory/user
        Transaction tx = transactionManager.returnBook(copyId);

        // If fines exist, you may want to notify user or mark outstanding balance
        if (tx.getFineAmount() > 0) {
            // Example: userManager.addFine(tx.getUserId(), tx.getFineAmount());
            // We left this out because your UserManager implementation may vary.
        }

        return tx;
    }

    // Query helpers

    public synchronized Optional<PhysicalBook> findAnyAvailableCopy(String name, String author, int edition) {
        Book book = bookCatalog.findBook(name, author, edition);
        if (book == null) return Optional.empty();
        return inventoryManager.getAnyAvailableCopy(book.getBookId());
    }

    public synchronized List<Transaction> getActiveTransactionsForUser(int userId) {
        return transactionManager.getActiveTransactionsForUser(userId);
    }

    public synchronized List<Transaction> getTransactionHistoryForUser(int userId) {
        return transactionManager.getTransactionHistoryForUser(userId);
    }

    public synchronized List<PhysicalBook> getAllCopiesForBook(int bookId) {
        return inventoryManager.getAllCopies(bookId);
    }
}