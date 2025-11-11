import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int id;
    List<BorrowedBook> borrowedBooks = new ArrayList<>();

    User( String name , int id ){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void borrowBook(Book book) {
        BorrowedBook bb = new BorrowedBook(book, LocalDate.now(), LocalDate.now().plusDays(14)); // 2-week loan
        borrowedBooks.add(bb);
    }

    public boolean returnBook(Book book) {
        return borrowedBooks.removeIf(bb -> bb.getBook().equals(book));
    }

    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    static class BorrowedBook {
        Book book;
        LocalDate borrowDate;
        LocalDate dueDate;

        BorrowedBook( Book book , LocalDate borrowDate , LocalDate dueDate ){
            this.book = book;
            this.borrowDate = borrowDate;
            this.dueDate = dueDate;
        }

        public Book getBook() {
            return book;
        }

        public LocalDate getBorrowDate() {
            return borrowDate;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        public void setBook(Book book) {
            this.book = book;
        }

        public void setBorrowDate(LocalDate borrowDate) {
            this.borrowDate = borrowDate;
        }

        public void setDueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
        }
    }

}
