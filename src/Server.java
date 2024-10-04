
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();  // Accept client connections
                System.out.println("A new client has connected!");

                ClientHandler clientHandler = new ClientHandler(socket);  // Handle the client in a separate thread
                clientHandlers.add(clientHandler);  // Keep track of active clients

                Thread thread = new Thread(clientHandler);  // Each client gets its own thread
                thread.start();
            }
        } catch (IOException e) {
            closeServerSocket();
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8010);  
        Server server = new Server(serverSocket);
        server.startServer();  
    }
}
