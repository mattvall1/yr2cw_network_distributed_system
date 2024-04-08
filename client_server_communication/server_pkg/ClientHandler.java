package client_server_communication.server_pkg;

import client_server_communication.Server;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Server implements Runnable {
    public static Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override // Override method in superclass
    public void run() {
        // Get scanner to read inputs from clients
        Scanner scan;
        try {
            scan = new Scanner(socket.getInputStream());

            // Get user id
            Integer user_id = Integer.valueOf(scan.nextLine());

            // Add client details into main hashmap
            ServerUtils.add_client_details(client_details, user_id, socket);

            // Send coordinator details to new clients
            if(client_details.size() > 1) ServerMessagingHelper.send_coordinator_info(user_id);

            // Loop always reading inputs for users
            while(scan.hasNextLine()) {
                String message = scan.nextLine();
                ServerUtils.console_output(user_id, message);
                ServerMessagingHelper.redirect_to_correct_message_routine(user_id, message);
            }

            // Check if coordinator has left and needs reassigning before we close connections
            UserDetails user_details = client_details.get(user_id);
            if(user_details.is_coordinator) {
                // TODO: Rewrite this to not use a loop
                // Reassign coordinator role to first client
                for (Integer client_id : client_details.keySet()) {
                    UserDetails user_to_update = client_details.get(client_id);
                    // First, make sure we don't update the user we're about to delete
                    if(!user_to_update.is_coordinator) {
                        // Remove the user from the main hashmap
                        client_details.remove(user_id);

                        // Set the is_coordinator param to true
                        user_to_update.is_coordinator = true;

                        // Re-add the user with the updated is_coordinator status
                        client_details.put(user_id, user_details);

                        // Set global coordinator id
                        current_coordinator = client_id;

                        // Tell the user that they are now the coordinator
                        ServerMessagingHelper.send_new_coordinator_info(client_id);

                        break;
                    }
                }
            }

            // Close connections
            ClientServerConnectionHelper.disconnect_routine(user_id, scan);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
