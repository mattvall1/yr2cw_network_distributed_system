package client_server_communication.server;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        // Define variables
        ServerSocket serverSocket = null;
        Socket socket = null;

        // Create server socket
        try {
            serverSocket = new ServerSocket(850);
        } catch (IOException e) {
            System.out.println("Connection failed");
        }

        try {
            // Connect to client socket
            socket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }

        // If we get to this point, a client is successfully connected
        System.out.println("IP: " + socket.getLocalAddress() + ", at port: " + socket.getLocalPort() + " connected");

        // Read data from client
        BufferedReader bufferedReader
                = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));

        // Server executes continuously
        while (true) {
            String recievedData;
            // Read from client + print
            while ((recievedData = bufferedReader.readLine()) != null) {
                System.out.println(recievedData);
            }

            // Close connections
            bufferedReader.close();
            serverSocket.close();
            socket.close();

        }

    }

}
