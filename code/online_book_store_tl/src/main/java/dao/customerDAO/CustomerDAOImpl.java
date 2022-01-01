package dao.customerDAO;

import dao.bookDAO.BookDAOImpl;
import dao.bookDAO.PublisherDAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.book.Author;
import model.customer.Account;
import model.customer.Customer;
import model.customer.CustomerNew;
import utils.db.ConnectDB;

/**
 *
 * @author Administrator
 */
public class CustomerDAOImpl implements CustomerDAO {
    private Connection connection;
     
    public CustomerDAOImpl() {
        this.connection = null;
    }
    
    
    @Override
    public String createCustomer(CustomerNew customerNew) {
        if (existUsername(customerNew.getAccount().getUsername())) {
            return "username have already been existed";
        }
        
        String sql = "INSERT INTO `customer` (`Email`, `Phone`, `DateOfBirth`, `Type`, `Gender`) VALUES (?, ?, ?, ?, ?)";
        try {     
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, customerNew.getEmail());
            statement.setString(2, customerNew.getPhone());
            statement.setDate(3, new java.sql.Date(customerNew.getDateOfBirth().getTime()));
            statement.setString(4,customerNew.getType());
            statement.setString(5, customerNew.getGender());
            statement.executeUpdate();
            ResultSet generatedKeys  = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                int customerID = generatedKeys.getInt(1);
                
                sql = "INSERT INTO `customernew` (`Note`, `CustomerID`) VALUES (?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, customerNew.getNote());
                statement.setInt(2, customerID);
                boolean rowInserted = statement.executeUpdate() > 0;
                if (!rowInserted) {
                    generatedKeys.close();
                    statement.close();
                    return "fail";
                }
                
                sql = "INSERT INTO `address` (`CustomerID`, `City`, `District`, `Street`, `HouseNumber`) VALUES (?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, customerID);
                statement.setString(2, customerNew.getAddress().getCity());
                statement.setString(3, customerNew.getAddress().getDistrict());
                statement.setString(4, customerNew.getAddress().getStreet());
                statement.setString(5, customerNew.getAddress().getHouseNumber());
                rowInserted = statement.executeUpdate() > 0;
                if (!rowInserted) {
                    generatedKeys.close();
                    statement.close();
                    return "fail";
                }
                
                sql = "INSERT INTO `fullname` (`CustomerID`, `FirstName`, `MiddleName`, `LastName`) VALUES (?, ?, ?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, customerID);
                statement.setString(2, customerNew.getFullName().getFirstName());
                statement.setString(3, customerNew.getFullName().getMiddleName());
                statement.setString(4, customerNew.getFullName().getLastName());
                rowInserted = statement.executeUpdate() > 0;
                if (!rowInserted) {
                    generatedKeys.close();
                    statement.close();
                    return "fail";
                }
                
                sql = "INSERT INTO `account` (`CustomerID`, `Username`, `Password`) VALUES (?, ?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, customerID);
                statement.setString(2, customerNew.getAccount().getUsername());
                statement.setString(3, customerNew.getAccount().getPassword());
                rowInserted = statement.executeUpdate() > 0;
                if (!rowInserted) {
                    generatedKeys.close();
                    statement.close();
                    return "fail";
                }
                
                generatedKeys.close();
                statement.close();
                return "success";
                
            } else {
                generatedKeys.close();
                statement.close();
                return "fail";
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return "fail";
    }
    
    private boolean existUsername(String username) {
        String sql = "SELECT * FROM `account` WHERE `Username` = ?";
        boolean result = false;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                result = true;
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return result;
    }

    @Override
    public Account login(Account account) {
        String sql = "SELECT * FROM `account` WHERE `Username` = ? AND `Password` = ?";
        Account result = new Account();
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                result = new Account(resultSet.getInt("ID"), resultSet.getString("username"), resultSet.getString("password"));
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return result;
    }

    @Override
    public int getIdCustomerByUsername(String username) {
        String sql = "SELECT `customer`.`ID` FROM `account`, `customer`"
                + " WHERE `account`.`CustomerID` = `customer`.`ID`"
                + " AND `account`.`Username` = ?";
        int id = 0;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                id = resultSet.getInt("ID");
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           ConnectDB.closeConnection(connection);
        }
        
        return id;
    }
    
}
