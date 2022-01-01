package dao.OrderDAO;

import dao.bookDAO.BookItemDAO;
import dao.bookDAO.BookItemDAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pair;
import model.book.BookItem;
import model.order.Cart;
import utils.db.ConnectDB;

/**
 *
 * @author Administrator
 */
public class CartDAOImpl implements CartDAO {

    private Connection connection;
    private BookItemDAO bookItemDAO;
     
    public CartDAOImpl() {
        this.connection = null;
        this.bookItemDAO = new BookItemDAOImpl();
    }
    
    @Override
    public boolean createCart(Cart cart, int orderId, int customerId) {
        String sql = "INSERT INTO `cart` (`CustomerID`, `OrderID`, `TotalAmount`, `TotalPrice`, `CreateDate`) VALUES (?, ?, ?, ?, ?)";
        boolean rowInserted = false;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, customerId);
            statement.setInt(2, orderId);
            statement.setFloat(3, cart.getTotalQuantity());
            statement.setFloat(4, cart.getTotalPrice());
            statement.setDate(5, new java.sql.Date(cart.getCreateDate().getTime()));
            statement.executeUpdate();
            ResultSet generatedKeys  = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int cartId = generatedKeys.getInt(1);
                List<Pair<BookItem, Integer>> listBookItems = cart.getListBookItems();
                for (Pair<BookItem, Integer> bookItemPair: listBookItems) {
                    sql = "INSERT INTO `cart_bookitem` (`CartID`, `BookItemID`, `Quantity`, `Price`, `Discount`) VALUES (?, ?, ?, ?, ?)";
                    statement = connection.prepareStatement(sql);
                    statement.setInt(1, cartId);
                    statement.setInt(2, bookItemPair.getKey().getId());
                    statement.setInt(3, bookItemPair.getValue());
                    statement.setFloat(4, bookItemPair.getKey().getPrice());
                    statement.setString(5, bookItemPair.getKey().getDiscount());
                    rowInserted = statement.executeUpdate() > 0;
                    if (rowInserted == false) {
                        break;
                    }
                }
            }
            
            generatedKeys.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return rowInserted;
    }


    @Override
    public Cart getCartByOrderId(int orderId) {
        Cart cart = new Cart();
        String sql = "SELECT * FROM `cart` WHERE `OrderID` = ?";
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int totalQuantity = resultSet.getInt("TotalAmount");
                float totalPrice = resultSet.getFloat("TotalPrice");
                Date createDate = resultSet.getDate("CreateDate");
                
                List<Pair<BookItem, Integer>> listBookItems = new ArrayList<>();
                sql = "SELECT * FROM `cart_bookitem` WHERE `CartID` = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int bookItemId = resultSet.getInt("BookItemID");
                    int quantity = resultSet.getInt("Quantity");
                    Float price = resultSet.getFloat("Price");
                    String discount = resultSet.getString("Discount");
                    
                    BookItem bookItem = bookItemDAO.getBookItemById(bookItemId);
                    bookItem.setDiscount(discount);
                    bookItem.setPrice(price);
                    
                    Pair<BookItem, Integer> bookItemPair = new Pair<>(bookItem, quantity);
                    listBookItems.add(bookItemPair);
                }
                
                cart = new Cart(id, totalQuantity, totalPrice, createDate, listBookItems);
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return cart;
    }
    
}
