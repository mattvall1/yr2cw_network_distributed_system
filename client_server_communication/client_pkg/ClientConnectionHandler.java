package client_server_communication.client_pkg;
// Import packages
import java.io.*;
import java.net.Socket;

public class ClientConnectionHandler implements Runnable {
    private final Socket client_socket;

    public ClientConnectionHandler(Socket client_socket) {
        this.client_socket = client_socket;
    }

    @Override
    public void run() {
        try {

            PrintWriter out = new PrintWriter(this.client_socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.client_socket.getInputStream()));

            ClientMessagingHandler message_handler = new ClientMessagingHandler(in,out,this.client_socket);
            new Thread(message_handler).start();

            message_handler.send_user_details();
            ClientUtils.initial_menu(message_handler.user_id, true);
            message_handler.send();

            // Close program completely
            System.exit(0);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
