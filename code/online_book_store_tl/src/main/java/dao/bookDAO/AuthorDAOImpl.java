package dao.bookDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class AuthorDAOImpl implements AuthorDAO{
    private Connection connection;
     
    public AuthorDAOImpl() {
        this.connection = null;
    }
   
    @Override
    public Author getAuthorById(int authorId) {
        Author author = new Author();
        String sql = "SELECT * FROM `author` WHERE `ID` = ?";
        try {
            connection = ConnectDB.getConnection();
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, authorId);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String fullName = resultSet.getString("FullName");
                String biography = resultSet.getString("Biography");
                String address = resultSet.getString("Address");
                
                author = new Author(id, fullName, biography, address);
            }
            
            resultSet.close();
            statement.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return author;
    }

    @Override
    public List<Author> getAuthorsByBookId(int bookId) {
        List<Author> listAuthors = new ArrayList<>();
        String sql = "SELECT `author`.* FROM `author`, `book_author`" +
                    " WHERE `book_author`.`AuthorID` = `author`.`ID`"+
                    " AND `book_author`.`BookID` = ?";
        
        try {
            connection = ConnectDB.getConnection();
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bookId);
            
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String fullName = resultSet.getString("FullName");
                String biography = resultSet.getString("Biography");
                String address = resultSet.getString("Address");
                
                Author author = new Author(id, fullName, biography, address);
                listAuthors.add(author);
            }
            
            resultSet.close();
            statement.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return listAuthors;
    }

    @Override
    public List<Author> searchAuthorByName(String authorName) {
        List<Author> listAuthors = new ArrayList<>();
        String sql = "SELECT * FROM `author` WHERE `FullName` LIKE ?";
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + authorName + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String fullName = resultSet.getString("FullName");
                String biography = resultSet.getString("Biography");
                String address = resultSet.getString("Address");
                
                Author author = new Author(id, fullName, biography, address);
                listAuthors.add(author);
            }
            
            resultSet.close();
            statement.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return listAuthors;
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> listAuthors = new ArrayList<>();
        String sql = "SELECT * FROM `author`";
        try {
            connection = ConnectDB.getConnection();
            
            PreparedStatement statement = connection.prepareStatement(sql);
            
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String fullName = resultSet.getString("FullName");
                String biography = resultSet.getString("Biography");
                String address = resultSet.getString("Address");
                
                Author author = new Author(id, fullName, biography, address);
                listAuthors.add(author);
            }
            
            resultSet.close();
            statement.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return listAuthors;
    }

    @Override
    public boolean createAuthor(Author author) {
        String sql = "INSERT INTO `author` (`FullName`, `Biography`, `Address`) VALUES (?, ?, ?)";
        boolean rowInserted = false;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, author.getFullName());
            statement.setString(2, author.getBiography());
            statement.setString(3, author.getAddress());
            
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return rowInserted;
    }

    
}
