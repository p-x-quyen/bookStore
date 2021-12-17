package dao.bookDAO;

import java.util.List;
import model.book.Publisher;

/**
 *
 * @author Administrator
 */
public interface PublisherDAO {
    Publisher getPublisherById(int publisherId);
    List<Publisher> getAllPublishers();
}
