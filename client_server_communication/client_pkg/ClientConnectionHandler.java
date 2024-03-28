package client_server_communication.client_pkg;

import java.io.*;
import java.net.Socket;

public class ClientConnectionHandler implements Runnable {
    private final Socket client_socket;

    public ClientConnectionHandler(Socket client_socket) {
        this.client_socket = client_socket;
    }

    @Override
    public void run() {

    }
}
