package utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ConnectDB {
    public static Connection getConnection() throws ClassNotFoundException, SQLException  {
        String jdbcURL = "jdbc:mysql://localhost:3306/online_book_store_tl?useSSL=false";
        String jdbcUsername = "root";
        String jdbcPassword = "";
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            
    }
    
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
