package client_server_communication;

import java.io.*;
import java.util.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    // List to store connected clients
    public static List<String> connected_clients = new ArrayList<>();

    public static void main(String args[]) throws Exception {

        // Create server socket
        try (ServerSocket serverSocket = new ServerSocket(850)) {

            // Server executes continuously
            while (true) {
                // Connect to client socket
                Socket socket = serverSocket.accept();

                // Handle client connection in a new thread
                new Thread(new ClientHandler(socket)).start();

                // If we get to this point, a client is successfully connected
                System.out.println("Client connected at ip: " + socket.getLocalAddress() + ", port: " + socket.getLocalPort());

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
            // Get scanner to read inputs from clients
            Scanner scan;
            try {
                scan = new Scanner(socket.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Loop always reading inputs for users
            while(scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }

        }
    }

}
