
import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket socket;
    private String username;

    // Constructor for creating the client with the username
    public Client(String username) {
        this.username = username;
    }

    // Method to connect to the server and start the ChatClient
    public void connectToServer() {
        try {
            // Initialize the socket connection to the server
            socket = new Socket("localhost", 8010);
            System.out.println("Connected to the chat server.");

            // After establishing the connection, initialize the ChatClient
            ChatClient chatClient = new ChatClient(socket, username);
            chatClient.sendMessage(); // Start message sending functionality

        } catch (IOException e) {
            System.out.println("Error connecting to the server: " + e.getMessage());
            closeSocket();
        }
    }

    // Method to close the socket in case of an error or disconnection
    private void closeSocket() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing the socket: " + e.getMessage());
        }
    }

    // Main method to start the client and pass the username
    public static void main(String[] args) {
        // For now, we're passing a hardcoded username, but this will come from AuthenticationHandler or ChatroomCLI
        String username = "testUser"; // Replace this with the actual username from the login process
        Client client = new Client(username);
        client.connectToServer(); // Connect to the server
    }
}
