package dao.bookDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.book.Author;
import model.book.Book;
import model.book.Publisher;
import utils.db.ConnectDB;

/**
 *
 * @author Administrator
 */
public class BookDAOImpl implements BookDAO{
    private Connection connection;
    private AuthorDAO authorDAO;
    private PublisherDAO publisherDAO;
     
    public BookDAOImpl() {
        try {
            this.connection = ConnectDB.getConnection();
            this.authorDAO = new AuthorDAOImpl();
            this.publisherDAO = new PublisherDAOImpl();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public List<Book> getAllBooks() {
        List<Book> listBooks = new ArrayList<>();
        String sql = "SELECT * FROM `book`";
        
        try {    
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int publisherID = resultSet.getInt("PublisherID");
                String name = resultSet.getString("Name");
                String summary = resultSet.getString("Summary");
                int numberOfPages = resultSet.getInt("NumberOfPages");
                String language = resultSet.getString("Language");
                String isbn = resultSet.getString("ISBN");
                
                List<Author> listAuthors = authorDAO.getAuthorsByBookId(id);
                Publisher publisher = publisherDAO.getPublisherById(publisherID);
                
                Book book = new Book(id, name, summary, numberOfPages, language, isbn, listAuthors, publisher);
                listBooks.add(book);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listBooks;
    }
    
}
