package dao.bookDAO;

import java.util.List;
import model.book.Author;

/**
 *
 * @author Administrator
 */
public interface AuthorDAO {
    Author getAuthorById(int authorId);
    List<Author> getAuthorsByBookId(int bookId);
}
