package client_server_communication.server_pkg;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ServerUtils {
    // Console output routine for server logging
    public static void console_output(Integer sender_id, String message) {
        // Create datetime string to put on every output
        String current_time = get_current_datetime() + " | ";
        if (message.matches("^brm-.*")) {
            // Remove regex from string
            message = message.replaceFirst("^brm-", "");

            // Print to console
            System.out.println(current_time + "Broadcast from " + sender_id + ": " + message);
        } else if (message.matches("^dm-.*")) {
            // Get index of first underscore after user ID, to split message properly
            int next_dash = message.indexOf("-", 3);
            int receiver = Integer.parseInt(message.substring(3, next_dash));

            // Print to console
            System.out.println(current_time + "Direct message from " + sender_id + " to " + receiver + ": " + message.substring(next_dash + 1));
        } else if(message.matches("^exit$")) {
            System.out.println(current_time + "User " + sender_id + " will now be disconnected...");
        } else if(message.matches("^act-grp-details$")) {
            System.out.println(current_time + "Coordinator requested active group details");
        } else if(message.matches("^grp-details$")) {
            System.out.println(current_time + "User " + sender_id + " requested group details");
        } else {
            // This code shouldn't run, keep this for potential debugging
            System.out.println(current_time + "RECEIVED MESSAGE: " + message);
        }
    }

    public static void add_client_details(HashMap client_details, Integer user_id, Socket socket) throws IOException {
        Boolean is_coordinator = client_details.isEmpty();

        // Create instance of UserDetails to add client information
        UserDetails user_details = new UserDetails(socket, is_coordinator);

        // Next, add user details to list of clients
        client_details.put(user_id, user_details);

        // If this user is the coordinator, tell them.
        if(is_coordinator) ServerMessagingHelper.send_new_coordinator_info(user_id);
    }

    public static String get_current_datetime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");

        return now.format(formatter);
    }

}
