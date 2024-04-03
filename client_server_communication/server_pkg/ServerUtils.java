package client_server_communication.server_pkg;

import java.net.Socket;
import java.util.HashMap;

public class ServerUtils {
    // Console output routine for server logging
    public static void console_output(Integer sender_id, String message) {
        if (message.matches("^brm-.*")) {
            // Remove regex from string
            message = message.replaceFirst("^brm-", "");

            // Print to console
            System.out.println("Broadcast from " + sender_id + ": " + message);
        } else if (message.matches("^dm-.*")) {
            // Get index of first underscore after user ID, to split message properly
            int next_dash = message.indexOf("-", 3);
            int receiver = Integer.parseInt(message.substring(3, next_dash));

            // Print to console
            System.out.println("Direct message " + sender_id + " -> " + receiver + ":" + message.substring(next_dash + 1));
        } else if(message.matches("^exit$")) {
            System.out.println("User " + sender_id + " will now be disconnected...");
        } else {
            // This code shouldn't run
            System.out.println("RECEIVED MESSAGE: " + message);
        }
    }

//    public static boolean assign_new_coordinator(HashMap client_details) {
//        return client_details.isEmpty();
//    }

    public static void add_client_details(HashMap client_details, Integer user_id, Socket socket) {
        // First, check if we need to assign this user to be coordinator

        // Create instance of UserDetails to add client information to
        UserDetails user_details = new UserDetails(socket, client_details.isEmpty());

        // Next, add user details to list of clients
        client_details.put(user_id, user_details);
    }

}
