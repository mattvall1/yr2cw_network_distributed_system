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

            // First, check if we are the first user in the group, then give them 'coordinator' status
            if(client_details.isEmpty()) {
                is_coordinator = true;
            }
            UserDetails user_details = new UserDetails(socket, is_coordinator);

            // Next, add user details to list of clients
            client_details.put(user_id, user_details);

            // Loop always reading inputs for users
            while(scan.hasNextLine()) {
                String message = scan.nextLine();
                ServerUtils.console_output(user_id, message);
                ServerMessagingHelper.redirect_to_correct_message_routine(user_id, message);
            }
            //TODO: Close connections in class
            ClientServerConnectionHelper.disconnect_routine(user_id);
            socket.close();
            scan.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
