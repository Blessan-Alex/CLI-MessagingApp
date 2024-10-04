
import java.io.*;
import java.net.Socket;


public class AuthenticationHandler {
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    public AuthenticationHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    // Register a new user
    public boolean registerUser(String username, String password) throws IOException {
        // Send registration request to the server
        bufferedWriter.write("/register");
        bufferedWriter.newLine();
        bufferedWriter.flush();

        bufferedWriter.write(username);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        bufferedWriter.write(password);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        // Read server response
        String response = bufferedReader.readLine();
        return response.equals("register successful");
    }

    // Login an existing user
    public boolean loginUser(String username, String password) throws IOException {
        // Send login request to the server
        bufferedWriter.write("/login");
        bufferedWriter.newLine();
        bufferedWriter.flush();

        bufferedWriter.write(username);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        bufferedWriter.write(password);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        // Read server response
        String response = bufferedReader.readLine();
        return response.equals("login successful");
    }

    // Start the chat client if login is successful
    public void startChatClient() {
        try {
            // Initialize the ChatClient to manage chat interactions
            ChatClient chatClient = new ChatClient(socket,"");
            chatClient.sendMessage(); // Start chat client
        } catch (Exception e) {
            System.out.println("Error starting chat client: " + e.getMessage());
        }
    }
}
