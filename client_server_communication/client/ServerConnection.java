package client_server_communication.client;

import java.io.*;
import java.net.*;

public class ServerConnection {
    public static void connectToServer(String host, int port) throws IOException {
        /* Create client socket */
        Socket socket = new Socket(host, port);
        System.out.println("Connected to server");

        // Stream to send data to server
        DataOutputStream dataOutput
                = new DataOutputStream(
                socket.getOutputStream());

        // to read data from the keyboard
        BufferedReader kb
                = new BufferedReader(
                    new InputStreamReader(System.in));

        String toSend;

        // Repeat if exit is not typed
        while (!(toSend = kb.readLine()).equals("exit")) {
            // Send to  server
            dataOutput.writeBytes(toSend + "\n");
        }

        // Close connection
        dataOutput.close();
        kb.close();
        socket.close();
    }
}
