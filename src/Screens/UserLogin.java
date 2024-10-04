
package Screens;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Util.HashUtil;
import Db.DatabaseConnection;

public class UserLogin {

    public boolean login() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username to login: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Hash the password to compare with stored hashed password
        String hashedPassword;
        
        hashedPassword = HashUtil.hashPassword(password);
        

        // Check credentials against the database
        DatabaseConnection dbConnection = new DatabaseConnection();
        try (Connection connection = dbConnection.getConnection()) {
            String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Login successful! Welcome, " + username + "!");
                return true; // Successful login
            } else {
                System.out.println("Login failed: Invalid username or password.");
                return false; // Failed login
            }
        } catch (SQLException e ) {
            System.out.println("Error during login: " + e.getMessage());
            return false;
        }
    }
}
