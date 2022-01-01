package dao.OrderDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.order.Cart;
import model.order.Order;
import model.order.Shipment;
import utils.db.ConnectDB;

/**
 *
 * @author Administrator
 */
public class ShipmentDAOImpl implements ShipmentDAO {
    private Connection connection;
     
    public ShipmentDAOImpl() {
        this.connection = null;
    }
    
    @Override
    public int createShipment(Shipment shipment, int orderId) {
        String sql = "INSERT INTO `shipment` (`OrderID`, `Type`, `Code`, `Address`, `Price`) VALUES (?, ?, ?, ?, ?)";
        int shipmentId = 0;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, orderId);
            statement.setString(2, shipment.getType());
            statement.setString(3, shipment.getCode());
            statement.setString(4, shipment.getAddress());
            statement.setFloat(5, shipment.getPrice());
            statement.executeUpdate();
            ResultSet generatedKeys  = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                shipmentId = generatedKeys.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShipmentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShipmentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return shipmentId;
    }

    @Override
    public Shipment getShipmentByOrderId(int orderId) {
        String sql = "SELECT * FROM `shipment` WHERE `OrderID` = ?";
        Shipment shipment = new Shipment();
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String type = resultSet.getString("Type");
                String code = resultSet.getString("Code");
                String address = resultSet.getString("Address");
                Float price = resultSet.getFloat("Price");
                shipment = new Shipment(id, type, code, address, price);
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
        
        return shipment;
    }
    
}
