package client_server_communication.server_pkg;

import client_server_communication.Server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientServerConnectionHelper extends Server {

    public static void disconnect_routine(Integer user_id, Scanner scan) throws IOException {
        // First, close scanner
        scan.close();
        // Next, get client socket and close
        UserDetails user_details = client_details.get(user_id);
        Socket client_socket = user_details.socket;
        client_socket.close();

        // Finally, remove user from map
        client_details.remove(user_id);
        System.out.println("User " + user_id + " successfully disconnected. ");
    }

}
