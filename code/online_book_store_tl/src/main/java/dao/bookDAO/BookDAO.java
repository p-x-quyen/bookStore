package dao.bookDAO;

import java.util.List;
import model.book.Book;

/**
 *
 * @author Administrator
 */
public interface BookDAO {
    List<Book> getAllBooks();
    Book getBookById(int bookId);
    boolean hasBookItem(int bookId);
}
