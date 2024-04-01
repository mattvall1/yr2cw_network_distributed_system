package client_server_communication.server_pkg;

public class ServerUtils {
    // Console output routine for server logging
    public static void console_output(Integer sender_id, String message) {
        if (message.matches("^broadcast_.*")) {
            // Remove regex from string
            message = message.replaceFirst("^broadcast_", "");

            // Print to console
            System.out.println("Broadcast from " + sender_id + ": " + message);
        } else if (message.matches("^direct_message_.*")) {
            // Remove regex from string
            message = message.replaceFirst("^direct_message_.*", "");

            // Get index of first underscore after user ID, to split message properly
            int next_underscore = message.indexOf("_", 15);
            int receiver = Integer.parseInt(message.substring(15, next_underscore));
            message = message.substring(next_underscore + 1);

            // Print to console
            System.out.println("Direct message " + sender_id + " -> " + receiver + ":" + message);
        } else {
            System.out.println("RECEIVED MESSAGE: " + message);
        }
    }
}
