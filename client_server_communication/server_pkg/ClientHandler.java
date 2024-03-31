package client_server_communication.server_pkg;

import client_server_communication.Server;
import java.io.IOException;
import java.io.PrintWriter;
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

            // First get the user id and add to map of clients
            Integer user_id = Integer.valueOf(scan.nextLine());
            client_map.put(user_id, socket);

            // Loop always reading inputs for users
            while(scan.hasNextLine()) {
                String message = scan.nextLine();
                ServerUtils.console_output(message);
                MessagingHelper.redirect_message(user_id, message);
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
