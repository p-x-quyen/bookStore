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
    List<Author> searchAuthorByName(String authorName);
    List<Author> getAllAuthors();
    boolean createAuthor(Author author);
    boolean checkExistId(int authourId);
}
