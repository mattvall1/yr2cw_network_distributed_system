package client_server_communication.client;
import java.io.*;

public class Client {
    public static void main(String[] args) throws Exception {
        // Get inputs from user
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter user ID: ");
        String userID = bufferedReader.readLine();
        System.out.println("Enter IP of server: ");
        String ip = bufferedReader.readLine();
        System.out.println("Enter port: ");
        String port = bufferedReader.readLine();
        // TODO: Add validity checks on inputs

        // TEMP - Set IP and port every time
        ip = "localhost";
        port = String.valueOf(850);

        ServerConnection.connectToServer(ip, Integer.parseInt(port), Integer.parseInt(userID));


    }
}
