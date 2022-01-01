package dao.OrderDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.order.Cash;
import model.order.Check;
import model.order.Credit;
import model.order.Payment;
import model.order.Shipment;
import utils.db.ConnectDB;

/**
 *
 * @author Administrator
 */
public class PaymentDAOImpl implements PaymentDAO {
    private Connection connection;
     
    public PaymentDAOImpl() {
        this.connection = null;
    }
    
    @Override
    public boolean createPayment(Object object, int orderId, int shipmentId) {
        boolean rowInserted = false;
        if (object instanceof Check) {
            Check check = (Check) object;
            
            String sql = "INSERT INTO `payment` (`OrderID`, `ShipmentID`, `TotalPrice`, `Type`) VALUES (?, ?, ?, ?)";
            try {
                connection = ConnectDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, orderId);
                statement.setInt(2, shipmentId);
                statement.setFloat(3, check.getTotalPrice());
                statement.setString(4, check.getType());
                statement.executeUpdate();
                ResultSet generatedKeys  = statement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    int paymentId = generatedKeys.getInt(1);
                    rowInserted = createCheckPayment(check, paymentId);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PaymentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PaymentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
               ConnectDB.closeConnection(connection);
            }
        } else if (object instanceof Credit) {
            // do sth
        } else if (object instanceof Cash) {
            // do sth
        }
        
        return rowInserted;
    }
    
    private boolean createCheckPayment(Check check, int paymentId) {
        String sql = "INSERT INTO `check` (`Name`, `BankID`, `PaymentID`) VALUES (?, ?, ?)";
        boolean rowInserted = false;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, check.getName());
            statement.setString(2, check.getBankID());
            statement.setInt(3, paymentId);
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
    public Payment getPaymentByOrderId(int orderId) {
        String sql = "SELECT * FROM `payment` WHERE `OrderID` = ?";
        Payment payment = new Payment();
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                float totalPrice = resultSet.getFloat("TotalPrice");
                String type = resultSet.getString("Type");
                payment = new Payment(id, totalPrice, type);
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ShipmentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShipmentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return payment;
    }
    
}
