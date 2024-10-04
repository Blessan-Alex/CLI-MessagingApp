
package Screens;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import Util.HashUtil;
import Db.DatabaseConnection;

public class UserRegistration {

    public boolean registerUser() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username to register: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Hash the password using HashUtil
        String hashedPassword = HashUtil.hashPassword(password);

        // Store user details in the database
        DatabaseConnection dbConnection = new DatabaseConnection();
        try (Connection connection = dbConnection.getConnection()) {
            String query = "INSERT INTO Users (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registration successful!");
                return true;
            } else {
                System.out.println("Registration failed. Try again.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error during registration: " + e.getMessage());
            return false;
        }
    }
}
