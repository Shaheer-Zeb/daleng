package database;

/**
 * 
 * @author ShaheerZK
 */
import java.sql.*;

public class DBConnection 
{
    // Later if we host a cloud SQL server, paid one, of course :(, we can copy our local database there and use its credentials.
    private static final String URL = "jdbc:mysql://localhost:3306/uni_course";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConnection()
    {
        try 
        {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } 
        catch (SQLException ex) 
        {
            System.getLogger(DBConnection.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
}
