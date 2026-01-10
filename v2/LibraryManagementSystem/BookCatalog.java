package LibraryManagementSystem;
import java.util.*;

public class BookCatalog {

    private final Map<String, Book> booksByIsbn = new HashMap<>();
    private final Map<String, List<Book>> booksByTitle = new HashMap<>();
    private final Map<String, List<Book>> booksByAuthor = new HashMap<>();

    public void addBook(Book book) {
        booksByIsbn.put(book.getIsbn(), book);

        booksByTitle
                .computeIfAbsent(book.getName(), k -> new ArrayList<>())
                .add(book);

        booksByAuthor
                .computeIfAbsent(book.getAuthor(), k -> new ArrayList<>())
                .add(book);
    }

    public Book searchByIsbn(String isbn) {
        return booksByIsbn.get(isbn);
    }

    public List<Book> searchByTitle(String title) {
        return booksByTitle.getOrDefault(title, Collections.emptyList());
    }

    public List<Book> searchByAuthor(String author) {
        return booksByAuthor.getOrDefault(author, Collections.emptyList());
    }
}
