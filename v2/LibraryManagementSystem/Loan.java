package LibraryManagementSystem;

import java.time.LocalDate;

public class Loan {

    private final int loanId;
    private final BookItem bookItem;
    private final Member member;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;

    private LocalDate returnDate; // null => active loan

    public Loan(int loanId,
                BookItem bookItem,
                Member member,
                LocalDate borrowDate,
                LocalDate dueDate) {

        this.loanId = loanId;
        this.bookItem = bookItem;
        this.member = member;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public int getLoanId() {
        return loanId;
    }

    public BookItem getBookItem() {
        return bookItem;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isActive() {
        return returnDate == null;
    }

    public void markReturned(LocalDate returnDate) {
        if (this.returnDate != null) {
            throw new IllegalStateException("Loan already closed");
        }
        this.returnDate = returnDate;
    }
}