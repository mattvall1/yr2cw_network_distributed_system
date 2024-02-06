package client_server_communication.client;

import java.io.*;
import java.net.*;

public class ServerConnection {
    public static void connectToServer(String host, int port, int userID) throws IOException {
        /* Create client socket */
        Socket socket = new Socket(host, port);
        System.out.println("Connected to server");

        // Stream to send data to server
        DataOutputStream dataOutput
                = new DataOutputStream(
                socket.getOutputStream());

        // Send user ID to server (TODO: int datatype here)
        dataOutput.writeInt(userID);
        // Message handler - out


        // BufferedReader to read data from the keyboard
        BufferedReader kb
                = new BufferedReader(
                new InputStreamReader(System.in));

        String messageToSend;
        // Repeat if exit is not typed
        System.out.println("Input message: ");
        while (!(messageToSend = kb.readLine()).equals("exit")) {
            System.out.println("Input message: ");
            // Send to  server
            dataOutput.writeBytes(messageToSend + "\n");
        }

        // Close connection
        dataOutput.close();
        kb.close();
        socket.close();
    }
}
