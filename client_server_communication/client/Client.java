package client_server_communication.client;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        ServerConnection servConn = new ServerConnection();
        servConn.connectToServer("localhost", 850);


    }
}
