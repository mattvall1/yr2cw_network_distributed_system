package client_server_communication.server_pkg;

import client_server_communication.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Objects;

public class MessagingHelper extends ClientHandler {

    public MessagingHelper(Socket socket) {
        super(socket);
    }

    public static void redirect_message(Integer sender_id, String message) throws IOException {
        // Check message type
        // ASSUME EVERY MESSAGE IS BROADCAST
        if(message.matches("^broadcast_.*") || 1 == 1) {
            send_to_clients(sender_id, message);


        } else if(message.matches("^direct_message_.*")) {

        }
    }

    public static void send_to_clients(Integer sender_id, String message) throws IOException {

        // Loop through and send message back to each client as needed
        for (Integer client_id : client_map.keySet()) {
            System.out.println(client_id + ":" + message);
            try {
                // Get socket for user to send data to, then create new writer
                Socket client_socket = client_map.get(client_id);
                PrintWriter output = new PrintWriter(client_socket.getOutputStream(), true);
                // Send data (only to non-senders)
                if(!Objects.equals(sender_id, client_id)) {
                    output.println(sender_id + ":" + message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
