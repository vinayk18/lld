package LibraryManagementSystem;

public class Book {
    private final String isbn ;
    private final String name;
    private final String author;

    public Book(String name, String isbn, String author) {
        this.name = name;
        this.isbn = isbn;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
}
