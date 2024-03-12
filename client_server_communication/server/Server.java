package client_server_communication.server;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        // Create server socket
        try (ServerSocket serverSocket = new ServerSocket(850)) {

            // Server executes continuously
            while (true) {
                // Connect to client socket
                Socket socket = serverSocket.accept();

                // Handle client connection in a new thread
                new Thread(new ClientHandler(socket)).start();

                // If we get to this point, a client is successfully connected
                System.out.println("IP: " + socket.getLocalAddress() + ", at port: " + socket.getLocalPort() + " connected");
            }
        } catch (IOException err) {
            System.out.println("Error:" + err);
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override // Override method in superclass
        public void run() {
            try (DataInputStream dataInput = new DataInputStream(socket.getInputStream())) {
                var userID = (dataInput.readInt());

                // TODO: Check DB for user details, for previous conversations

                // Read data from client
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Define received data string
                String receivedData;

                // Read from client + print received text (this is always looping)
                // TODO: Send data back to ALL clients (simple GC feature)
                while ((receivedData = bufferedReader.readLine()) != null) {
                    // TODO: Have this send to the database (if time)
                    System.out.println(userID + ": " + receivedData);
                }
                System.out.println(userID + " left.");

                // Close connections
                bufferedReader.close();
                socket.close();
            } catch (IOException err) {
                System.out.println("Error:" + err);
            }
        }
    }
}
