public class PhysicalBook {

    private Integer copyId;
    private Book book;
    private COPY_STATUS status;

    PhysicalBook( int id , Book book ) {
        this.copyId = id;
        this.book = book;
        this.status = COPY_STATUS.AVAILABLE;
    }

    public Book getBook() {
        return book;
    }

    public COPY_STATUS getStatus() {
        return status;
    }

    public void setStatus(COPY_STATUS status) {
        this.status = status;
    }

    public Integer getCopyId() {
        return copyId;
    }

    public Integer getBookId() {
        return book.getBookId();
    }
}
