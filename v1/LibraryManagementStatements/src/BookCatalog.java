import java.util.HashMap;
import java.util.Map;

public class BookCatalog {

    // Two maps for fast lookup by both id and composite key
    private final Map<Integer, Book> booksById = new HashMap<>();
    private final Map<String, Integer> keyToId = new HashMap<>();

    // Adds a book if not already present (based on title+author+edition)
    public synchronized void addBook(Book book) {
        String key = generateKey(book.getName(), book.getAuthor(), book.getEdition());
        if (keyToId.containsKey(key)) {
            // already exists — do nothing (idempotent)
            return;
        }
        booksById.put(book.getBookId(), book);
        keyToId.put(key, book.getBookId());
    }

    // Retrieve book by id
    public synchronized Book getBook(int bookId) {
        return booksById.get(bookId);
    }

    // Find book by title, author, and edition
    public synchronized Book findBook(String name, String author, int edition) {
        String key = generateKey(name, author, edition);
        Integer id = keyToId.get(key);
        if (id == null) return null;
        return booksById.get(id);
    }

    // Soft-remove (mark REMOVED) a book
    public synchronized boolean removeBook(Book book) {
        Book existing = booksById.get(book.getBookId());
        if (existing == null) return false;
        existing.setStatus(BOOK_STATUS.REMOVED);
        return true;
    }

    // Optional: list all active books
    public synchronized Map<Integer, Book> getAllBooks() {
        return new HashMap<>(booksById);
    }

    private String generateKey(String name, String author, int edition) {
        // Normalize key to avoid duplicates due to case/whitespace
        return name.trim().toLowerCase() + "|" + author.trim().toLowerCase() + "|" + edition;
    }
}