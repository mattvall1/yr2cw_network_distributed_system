package client_server_communication.server_pkg;

import client_server_communication.Server;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
            boolean is_coordinator = false;

            // Get user id
            Integer user_id = Integer.valueOf(scan.nextLine());

            // Add client details into main hashmap
            ServerUtils.add_client_details(client_details, user_id, socket);

            // Loop always reading inputs for users
            while(scan.hasNextLine()) {
                String message = scan.nextLine();
                ServerUtils.console_output(user_id, message);
                ServerMessagingHelper.redirect_to_correct_message_routine(user_id, message);
            }
            // Check if coordinator has left and needs reassigning before we close connections
            UserDetails user_details = client_details.get(user_id);
            if(user_details.is_coordinator) {
                ServerUtils.assign_coordinator(client_details);
            }

            // Close connections
            ClientServerConnectionHelper.disconnect_routine(user_id, scan);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
