package LibraryManagementSystem;

public class BookItem {

    private final int id;
    private final Book book;
    private BookItemStatus status;

    public BookItem(int id, Book book) {
        this.id = id;
        this.book = book;
        this.status = BookItemStatus.AVAILABLE;
    }

    void markIssued() {
        if (status == BookItemStatus.ISSUED) {
            throw new IllegalStateException("BookItem already issued");
        }
        this.status = BookItemStatus.ISSUED;
    }

    void markAvailable() {
        if (status == BookItemStatus.AVAILABLE) {
            throw new IllegalStateException("BookItem already available");
        }
        this.status = BookItemStatus.AVAILABLE;
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public BookItemStatus getStatus() {
        return status;
    }
}