import java.time.LocalDateTime;

public class Transaction {
    private final int transactionId;
    private final int userId;
    private final int copyId;
    private final int bookId;
    private final LocalDateTime issueDate;
    private final LocalDateTime dueDate;

    private LocalDateTime returnDate;
    private double fineAmount;
    private TransactionStatus status;

    public Transaction(int transactionId, int userId, int copyId, int bookId,
                       LocalDateTime issueDate, LocalDateTime dueDate) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.copyId = copyId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = TransactionStatus.ISSUED;
    }

    // getters
    public int getTransactionId() { return transactionId; }
    public int getUserId() { return userId; }
    public int getCopyId() { return copyId; }
    public int getBookId() { return bookId; }
    public LocalDateTime getIssueDate() { return issueDate; }
    public LocalDateTime getDueDate() { return dueDate; }
    public LocalDateTime getReturnDate() { return returnDate; }
    public double getFineAmount() { return fineAmount; }
    public TransactionStatus getStatus() { return status; }

    // setters for return-flow
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }
    public void setFineAmount(double fineAmount) { this.fineAmount = fineAmount; }
    public void setStatus(TransactionStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Transaction#" + transactionId + " user=" + userId +
                " copy=" + copyId + " book=" + bookId + " status=" + status;
    }
}