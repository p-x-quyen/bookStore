package dao.bookDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.book.Author;
import model.book.Publisher;
import utils.db.ConnectDB;

/**
 *
 * @author Administrator
 */
public class PublisherDAOImpl implements PublisherDAO{
    private Connection connection;
     
    public PublisherDAOImpl() {
        try {
            this.connection = ConnectDB.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PublisherDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PublisherDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Publisher getPublisherById(int publisherId) {
        Publisher publisher = new Publisher();
        String sql = "SELECT * FROM `publisher` WHERE `ID` = ?";
        
        try {
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, publisherId);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                String email = resultSet.getString("Email");
                
                publisher = new Publisher(id, name, address, email);
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PublisherDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return publisher;
    }
    
}
