public class Book {
    private static int nextBookId = 1;

    private Integer bookId;
    private String name;
    private String author;
    private Integer edition;
    private BOOK_STATUS status;

    Book(String name, String author, Integer edition) {
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.bookId = nextBookId++;
        this.status = BOOK_STATUS.ACTIVE;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getEdition() {
        return edition;
    }

    public Integer getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return "Book#" + bookId + ": " + name + " by " + author + " (Edition " + edition + ")";
    }

    public void setStatus( BOOK_STATUS bookStatus ){
        this.status = bookStatus;
    }

    public BOOK_STATUS getStatus() {
        return status;
    }
}
