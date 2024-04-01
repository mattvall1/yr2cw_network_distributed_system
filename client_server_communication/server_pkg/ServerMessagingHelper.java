package client_server_communication.server_pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class ServerMessagingHelper extends ClientHandler {

    public ServerMessagingHelper(Socket socket) {
        super(socket);
    }

    // Redirect message as applicable
    public static void redirect_message(Integer sender_id, String message) throws IOException {
        // Check message type
        if(message.matches("^broadcast_.*")) {
            send_to_all_clients(sender_id, message);

        } else if(message.matches("^direct_message_.*")) {
            // Get index of first underscore after user ID, to split message properly
            int next_underscore = message.indexOf("_", 15);
            // Send message to client
            send_to_client(sender_id, Integer.parseInt(message.substring(15, next_underscore)), message.substring(next_underscore + 1));
        }
    }

    // Send to all clients (broadcast message)
    public static void send_to_all_clients(Integer sender_id, String message) throws IOException {

        // Loop through and send message back to each client as needed
        for (Integer client_id : client_map.keySet()) {
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

    // Send to all clients (private message)
    public static void send_to_client(Integer sender_id, Integer receiver_id, String message) throws IOException {

        // Loop through and send message back to each client as needed
        try {
            // Get socket for user to send data to, then create new writer
            Socket client_socket = client_map.get(receiver_id);
            PrintWriter output = new PrintWriter(client_socket.getOutputStream(), true);
            // Send data
            output.println(sender_id + ":" + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
