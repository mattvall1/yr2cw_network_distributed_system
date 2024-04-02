package client_server_communication;

import client_server_communication.server_pkg.ClientHandler;

import java.io.*;
import java.util.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    // List to store connected clients
    public static HashMap<Integer, Socket> client_details = new HashMap<>();

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

}
