package client_server_communication.server_pkg;

import client_server_communication.Server;

public class ClientServerConnectionHelper extends Server {
    public static void connection_routine(String message) {


    }
    public static void disconnect_routine(Integer user_id) {
        client_details.remove(user_id);
        System.out.println("User: " + user_id + " successfully disconnected.");
    }

}
