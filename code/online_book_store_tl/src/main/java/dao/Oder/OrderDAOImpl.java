package dao.Oder;

import dao.bookDAO.AuthorDAOImpl;
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
import model.order.Cart;
import model.order.Order;
import model.order.Payment;
import model.order.Shipment;
import utils.db.ConnectDB;

/**
 *
 * @author Administrator
 */
public class OrderDAOImpl implements OrderDAO {
    private Connection connection;
    private CartDAO cartDAO;
    private ShipmentDAO shipmentDAO;
    private PaymentDAO paymentDAO;
     
    public OrderDAOImpl() {
        this.connection = null;
        this.cartDAO = new CartDAOImpl();
        this.shipmentDAO = new ShipmentDAOImpl();
        this.paymentDAO = new PaymentDAOImpl();
    }
    
    @Override
    public boolean createOrder(Order order, int customerId, Shipment shipment, Object object) {
        String sql = "INSERT INTO `order` (`CustomerID`, `Status`) VALUES (?, ?)";
        boolean rowInserted = false;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, customerId);
            statement.setString(2, order.getStatus());
            statement.executeUpdate();
            ResultSet generatedKeys  = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);
                rowInserted = cartDAO.createCart(order.getCart(), orderId, customerId);
                if (rowInserted == true) {
                    int shipmentId = shipmentDAO.createShipment(shipment, orderId);
                    rowInserted = (shipmentId > 0);
                    if (rowInserted == true) {
                        rowInserted = paymentDAO.createPayment(object, orderId, shipmentId);
                    }
                }
            }
            
            generatedKeys.close();
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
    public Order getOrderById(int orderId) {
        String sql = "SELECT * FROM `order` WHERE `ID` = ?";
        Order order = new Order();
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                Date date = resultSet.getTimestamp("CreateDate");
                String status = resultSet.getString("Status");
                Cart cart = cartDAO.getCartByOrderId(id);
                order = new Order(id, date, status, cart);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return order;
    }

    @Override
    public List<Order> getAllOrdersByCustomerId(int customerId) {
        String sql = "SELECT * FROM `order` WHERE `CustomerID` = ? ORDER BY `CreateDate` DESC";
        List<Order> listOrders = new ArrayList<>();
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                Date date = resultSet.getTimestamp("CreateDate");
                String status = resultSet.getString("Status");
                listOrders.add(new Order(id, date, status));
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return listOrders;
    }
    
}
