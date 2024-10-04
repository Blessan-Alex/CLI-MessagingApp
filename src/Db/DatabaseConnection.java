package Db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/chatroom";
        String user = "root"; // Replace with your MySQL username
        String password = "bless"; // Replace with your MySQL password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the JDBC driver
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            e.printStackTrace();
            return null;
        }
    }
}