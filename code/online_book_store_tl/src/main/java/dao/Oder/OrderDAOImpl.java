package dao.Oder;

import dao.bookDAO.AuthorDAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.order.Cart;
import model.order.Order;
import utils.db.ConnectDB;

/**
 *
 * @author Administrator
 */
public class OrderDAOImpl implements OrderDAO {
    private Connection connection;
    private CartDAO cartDAO;
     
    public OrderDAOImpl() {
        this.connection = null;
        this.cartDAO = new CartDAOImpl();
    }
    
    @Override
    public boolean createOrder(Order order, int customerId) {
        String sql = "INSERT INTO `order` (`CustomerID`, `CreateDate`, `Status`) VALUES (?, ?, ?)";
        boolean rowInserted = false;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            statement.setDate(2, new java.sql.Date(order.getCreateDate().getTime()));
            statement.setString(3, "0");
            
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return rowInserted;
    }

    @Override
    public Order getOrderById(int orderId, int customerId) {
        String sql = "SELECT * FROM `order` WHERE `ID` = ? AND `CustomerID` = ?";
        Order order = new Order();
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            statement.setInt(2, orderId);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                Date date = resultSet.getDate("CreateDate");
                String status = resultSet.getString("Status");
                Cart cart = cartDAO.getCartByOrderId(id);
                order = new Order(id, date, status, cart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return order;
    }
    
}
