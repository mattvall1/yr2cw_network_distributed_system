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

    public static boolean assign_coordinator(Integer user_id, HashMap client_details) {
        boolean is_coordinator = false;
        // First, check if there is any clients in the system yet, if not, this user is the coordinator
        if(client_details.isEmpty()) {
            is_coordinator = true;
        } else {

        }
        return is_coordinator;
    }

    public static void add_client_details(HashMap client_details, Integer user_id, Socket socket) {
        // First, check if we need to assign this user to be coordinator
        boolean is_coordinator = assign_coordinator(user_id, client_details);

        // Create instance of UserDetails to add client information to
        UserDetails user_details = new UserDetails(socket, is_coordinator);

        // Next, add user details to list of clients
        client_details.put(user_id, user_details);
    }

}
