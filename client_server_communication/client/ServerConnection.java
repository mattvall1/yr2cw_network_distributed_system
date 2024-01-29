package client_server_communication.client;

import java.net.Socket;

public class ServerConnection {
    public static void connectToServer(String host, int port) {
        /* Create client socket */
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to server");
        } catch(Exception e) {
            System.out.println("Connection to server failed");
        }
        
    }
}
