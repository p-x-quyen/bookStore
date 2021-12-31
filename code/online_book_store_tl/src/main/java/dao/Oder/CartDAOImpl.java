package dao.Oder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.order.Cart;
import utils.db.ConnectDB;

/**
 *
 * @author Administrator
 */
public class CartDAOImpl implements CartDAO {

    private Connection connection;
     
    public CartDAOImpl() {
        this.connection = null;
    }
    
    @Override
    public boolean createCart(Cart cart, int orderId, int customerId) {
        String sql = "INSERT INTO `cart` (`CustomerID`, `OrderID`, `TotalAmount`, `TotalPrice`, `CreateDate`) VALUES (?, ?, ?, ?, ?)";
        boolean rowInserted = false;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            statement.setInt(2, orderId);
            statement.setFloat(3, cart.getTotalQuantity());
            statement.setFloat(4, cart.getTotalPrice());
            statement.setDate(5, new java.sql.Date(cart.getCreateDate().getTime()));
            
            rowInserted = statement.executeUpdate() > 0;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
