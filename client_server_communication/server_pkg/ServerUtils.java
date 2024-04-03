package client_server_communication.server_pkg;

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
        } else {
            System.out.println("RECEIVED MESSAGE: " + message);
        }
    }
}
