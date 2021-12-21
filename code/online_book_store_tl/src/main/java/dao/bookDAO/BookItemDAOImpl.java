package dao.bookDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.book.Author;
import model.book.Book;
import model.book.BookItem;
import model.book.Publisher;
import utils.db.ConnectDB;

/**
 *
 * @author Administrator
 */
public class BookItemDAOImpl implements BookItemDAO{
    private Connection connection;
    private BookDAO bookDAO;
     
    public BookItemDAOImpl() {
        try {
            this.connection = ConnectDB.getConnection();
            this.bookDAO = new BookDAOImpl();
        } catch (SQLException ex) {
            Logger.getLogger(BookItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public boolean createBookItem(BookItem bookItem) {
        String sql = "INSERT INTO `bookitem` (`BookID`, `Image`, `Price`, `Discount`) VALUES (?, ?, ?, ?)";
        try {
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bookItem.getBook().getId());
            statement.setString(2, bookItem.getImage());
            statement.setFloat(3, bookItem.getPrice());
            statement.setString(4, bookItem.getDiscount());
            
            boolean rowInserted = statement.executeUpdate() > 0;
            statement.close();
            
            return rowInserted;
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<BookItem> getAllBookItems() {
        List<BookItem> listBookItems = new ArrayList<>();
        String sql = "SELECT * FROM `bookitem`";
        
        try {
            
            PreparedStatement statement = connection.prepareStatement(sql);
            
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                int bookId = resultSet.getInt("BookID");
                String image = resultSet.getString("Image");
                String discount = resultSet.getString("Discount");
                float price = resultSet.getFloat("Price");
                
                Book book = bookDAO.getBookById(bookId);
                BookItem bookItem = new BookItem(id, image, price, discount, book);
                listBookItems.add(bookItem);
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listBookItems;
    }

    @Override
    public BookItem getBookItemById(int bookItemId) {
        BookItem bookItem = new BookItem();
        String sql = "SELECT * FROM `bookitem` WHERE `ID` = ?";
        
        try {
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bookItemId);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int bookId = resultSet.getInt("BookID");
                String image = resultSet.getString("Image");
                String discount = resultSet.getString("Discount");
                float price = resultSet.getFloat("Price");
                
                Book book = bookDAO.getBookById(bookId);
                bookItem = new BookItem(id, image, price, discount, book);
            }
            
            resultSet.close();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bookItem;
    }

    @Override
    public List<BookItem> searchBookItemByName(String bookName) {
        List<BookItem> listBookItems = new ArrayList<>();
        String sql = "SELECT `bookitem`.* FROM `bookitem`, `book` WHERE" +
                " `bookitem`.`BookID` = `book`.`ID`" +
                " AND `book`.`Name` LIKE ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + bookName + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int bookId = resultSet.getInt("BookID");
                String image = resultSet.getString("Image");
                String discount = resultSet.getString("Discount");
                float price = resultSet.getFloat("Price");
                
                Book book = bookDAO.getBookById(bookId);
                BookItem bookItem = new BookItem(id, image, price, discount, book);
                listBookItems.add(bookItem);
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listBookItems;        
    }

    @Override
    public boolean updateBookItem(BookItem bookItem) {
        String sql = "UPDATE `bookitem` SET `Price` = ?, `Discount` = ? WHERE `ID` = ?";
        try {
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setFloat(1, bookItem.getPrice());
            statement.setString(2, bookItem.getDiscount());
            statement.setInt(3, bookItem.getId());
            System.out.println(bookItem.getId());
            boolean rowInserted = statement.executeUpdate() > 0;
//            System.out.println(rowInserted);
            statement.close();
            
            return rowInserted;
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
}
