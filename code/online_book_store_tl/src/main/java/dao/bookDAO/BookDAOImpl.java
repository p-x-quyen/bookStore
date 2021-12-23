package dao.bookDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
        this.connection = null;
        this.authorDAO = new AuthorDAOImpl();
        this.publisherDAO = new PublisherDAOImpl();
    }
    

    @Override
    public List<Book> getAllBooks() {
        List<Book> listBooks = new ArrayList<>();
        String sql = "SELECT * FROM `book`";
        
        try {
            connection = ConnectDB.getConnection();
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
            
            resultSet.close();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return listBooks;
    }

    @Override
    public Book getBookById(int bookId) {
        Book book = new Book();
        String sql = "SELECT * FROM `book` WHERE `ID` = ?";
        
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bookId);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int publisherID = resultSet.getInt("PublisherID");
                String name = resultSet.getString("Name");
                String summary = resultSet.getString("Summary");
                int numberOfPages = resultSet.getInt("NumberOfPages");
                String language = resultSet.getString("Language");
                String isbn = resultSet.getString("ISBN");
                
                List<Author> listAuthors = authorDAO.getAuthorsByBookId(id);
                Publisher publisher = publisherDAO.getPublisherById(publisherID);
                
                book = new Book(id, name, summary, numberOfPages, language, isbn, listAuthors, publisher);
            }
            
            resultSet.close();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return book;
    }

    @Override
    public int hasBookItem(int bookId) {
        String sql = "SELECT * FROM `bookitem` WHERE `BookID` = ?";
        int bookItemId = 0;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bookId);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                bookItemId = resultSet.getInt("ID");
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return bookItemId;
    }

    @Override
    public List<Book> searchBookByName(String bookName) {
        List<Book> listBooks = new ArrayList<>();
        String sql = "SELECT * FROM `book` WHERE `Name` LIKE ?";
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + bookName + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
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
            
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return listBooks;
    }

    @Override
    public boolean createBook(Book book) {
        String sql = "INSERT INTO `book` (`PublisherID`, `Name`, `Summary`, `NumberOfPages`, `Language`, `ISBN`) VALUES (?, ?, ?, ?, ?, ?)";
        boolean rowInserted = false;
        try {
            connection = ConnectDB.getConnection();
            
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, book.getPublisher().getId());
            statement.setString(2, book.getName());
            statement.setString(3, book.getSummary());
            statement.setInt(4, book.getNumberOfPages());
            statement.setString(5, book.getLanguage());
            statement.setString(6, book.getIsbn());
            statement.executeUpdate();
            ResultSet generatedKeys  = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                int bookId = generatedKeys.getInt(1);
                List<Author> listAuthors = book.getListAuthors();
                for (int i = 0; i < listAuthors.size(); i++) {
                    sql = "INSERT INTO `book_author` (`BookID`, `AuthorID`) VALUES (?, ?)";
                    statement = connection.prepareStatement(sql);
                    statement.setInt(1, bookId);
                    statement.setInt(2, listAuthors.get(i).getId());
                    rowInserted = statement.executeUpdate() > 0;
                    if (rowInserted == false) {
                        break;
                    }
                }
            }
            generatedKeys.close();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return rowInserted;
    }
}
