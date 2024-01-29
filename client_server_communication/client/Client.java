package client_server_communication.client;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        // Create client socket
        try (Socket socket = new Socket("localhost", 850)) {
            System.out.println("Connected to server");
        } catch(Exception e) {
            System.out.println("Connection to server failed");
        }

    }
}
