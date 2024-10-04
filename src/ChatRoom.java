
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import Screens.UserLogin;
import Screens.UserRegistration;

public class ChatRoom {
    public static void main(String[] args) throws Exception {
        try {
            // Initialize socket connection (example IP and port)
            Socket socket = new Socket("localhost", 8010);
            Scanner scanner = new Scanner(System.in);
            AuthenticationHandler authHandler = new AuthenticationHandler(socket);

            System.out.println("Welcome to the Chat Room!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                

                // Handle registration
                UserRegistration register = new UserRegistration();
                if (register.registerUser()) {
                    System.out.println("Registration successful!");
                } else {
                    System.out.println("Registration failed.");
                }
            } else if (choice == 2) {
                
                // Handle login
                UserLogin login = new UserLogin();
                if (login.login()) {
                    System.out.println("Login successful! Connecting to chat...");
                    authHandler.startChatClient();  // Start the chat client after successful login
                } else {
                    System.out.println("Login failed.");
                }
            } else {
                System.out.println("Invalid option. Exiting.");
            }

            // Close the scanner and socket
            scanner.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
