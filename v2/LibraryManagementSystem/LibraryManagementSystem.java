package LibraryManagementSystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class LibraryManagementSystem {
    private final BookCatalog catalog = new BookCatalog();
    private final Map<Integer, Member> members = new HashMap<>();
    private final Map<Integer, BookItem> bookItems = new HashMap<>();
    private final Map<Integer, Loan> activeLoans = new HashMap<>();
    private final Map<Book, Queue<Reservation>> reservationsByBook = new HashMap<>();
    private int bookItemIdSeq = 1;
    private int loanIdSeq = 1;
    private int reservationIdSeq = 1;


    public void addBook(Book book, int copies) {
        catalog.addBook(book);

        for (int i = 0; i < copies; i++) {
            BookItem item = new BookItem(bookItemIdSeq++, book);
            bookItems.put(item.getId(), item);
        }
    }

    public void registerMember(Member member) {
        members.put(member.getMemberId(), member);
    }


    public Loan issueBook(String isbn, int memberId, int loanDays) {
        Member member = getMember(memberId);
        Book book = catalog.searchByIsbn(isbn);

        enforceBorrowLimit(member);
        enforceReservationPriority(book, member);

        BookItem availableCopy = findAvailableCopy(book);
        if (availableCopy == null) {
            throw new IllegalStateException("No available copies");
        }

        availableCopy.markIssued();

        Loan loan = new Loan(
                loanIdSeq++,
                availableCopy,
                member,
                LocalDate.now(),
                LocalDate.now().plusDays(loanDays)
        );

        activeLoans.put(loan.getLoanId(), loan);
        return loan;
    }


    public void returnBook(int loanId) {
        Loan loan = activeLoans.remove(loanId);
        if (loan == null) {
            throw new IllegalArgumentException("Invalid loan");
        }

        loan.markReturned(LocalDate.now());
        BookItem item = loan.getBookItem();
        item.markAvailable();

        processNextReservation(item.getBook());
    }


    public Reservation reserveBook(String isbn, int memberId, int holdHours) {
        Member member = getMember(memberId);
        Book book = catalog.searchByIsbn(isbn);

        if (findAvailableCopy(book) != null) {
            throw new IllegalStateException("Book is available, no need to reserve");
        }

        Queue<Reservation> queue =
                reservationsByBook.computeIfAbsent(book, b -> new LinkedList<>());

        ensureNotAlreadyReserved(queue, member);

        Reservation reservation = new Reservation(
                reservationIdSeq++,
                book,
                member,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(holdHours)
        );

        queue.offer(reservation);
        return reservation;
    }


    private Member getMember(int memberId) {
        Member m = members.get(memberId);
        if (m == null) {
            throw new IllegalArgumentException("Member not found");
        }
        return m;
    }

    private void enforceBorrowLimit(Member member) {
        long activeCount = activeLoans.values().stream()
                .filter(l -> l.getMember().equals(member))
                .count();

        if (activeCount >= member.getMaxAllowedBorrows()) {
            throw new IllegalStateException("Borrow limit exceeded");
        }
    }

    private void enforceReservationPriority(Book book, Member member) {
        Queue<Reservation> queue = reservationsByBook.get(book);
        if (queue == null || queue.isEmpty()) return;

        Reservation head = queue.peek();
        if (!head.getMember().equals(member)) {
            throw new IllegalStateException("Book reserved by another member");
        }
    }

    private BookItem findAvailableCopy(Book book) {
        return bookItems.values().stream()
                .filter(i -> i.getBook().equals(book))
                .filter(i -> i.getStatus() == BookItemStatus.AVAILABLE)
                .findFirst()
                .orElse(null);
    }

    private void processNextReservation(Book book) {
        Queue<Reservation> queue = reservationsByBook.get(book);
        if (queue == null) return;

        while (!queue.isEmpty()) {
            Reservation r = queue.peek();

            if (r.isExpired(LocalDateTime.now())) {
                r.markExpired();
                queue.poll();
            } else {
                break;
            }
        }
    }

    private void ensureNotAlreadyReserved(Queue<Reservation> queue, Member member) {
        for (Reservation r : queue) {
            if (r.getMember().equals(member) && r.isActive()) {
                throw new IllegalStateException("Already reserved");
            }
        }
    }
}