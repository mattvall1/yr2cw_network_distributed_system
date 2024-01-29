package client_server_communication.server;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        // Create server socket - listening
        try (ServerSocket serverSocket = new ServerSocket(850)) {
            // Connect to client socket
            Socket socket = serverSocket.accept();
        } catch(Exception e) {
            System.out.println("Connection to client failed");
        }

        // If we get to this point, a client is successfully connected
        System.out.println("Connected");


    }

}
