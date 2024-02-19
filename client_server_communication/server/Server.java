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
            try {
                // Read data from client
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


//                int userID = Integer.parseInt(bufferedReader.readLine());
//                System.out.println(userID);

//                // TODO: Check DB for user details, make sure ID is retrieved correctly
//                if(userID == 666) {
//                    System.out.println("ID successfully retrieved");
////                    bufferedReader.readLine() = "\n";
//                } else {
//
//                }

                // Define received data string
                String receivedData;

                // Read from client + print received text (this is always looping)
                // TODO: Send data back to ALL clients (simple GC feature)
                while ((receivedData = bufferedReader.readLine()) != null) {
                    System.out.println(receivedData);
                }

                // Close connections
                bufferedReader.close();
                socket.close();
            } catch (IOException err) {
                System.out.println("Error:" + err);
            }
        }
    }
}
