package Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/chatroom";  // Replace with your database URL
        String user = "root";  // Replace with your database username
        String password = "bless";  // Replace with your database password

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");  
            System.out.println("Driver loaded successfully!");

            // Establish the connection
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully!");

            // Close the connection after testing
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found. Ensure the correct MySQL JDBC driver is in the classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed. Check the URL, username, or password.");
            e.printStackTrace();
        }
    }
}
