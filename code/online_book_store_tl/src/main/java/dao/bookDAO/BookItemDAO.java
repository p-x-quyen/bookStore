package dao.bookDAO;

import java.util.List;
import model.book.BookItem;

/**
 *
 * @author Administrator
 */
public interface BookItemDAO {
    BookItem getBookItemById(int bookItemId);
    boolean createBookItem(BookItem bookItem);
    List<BookItem> getAllBookItems();
    List<BookItem> searchBookItemByName(String bookName);
}
