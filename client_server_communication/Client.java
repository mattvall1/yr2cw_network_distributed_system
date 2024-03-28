package client_server_communication;

// Import packages
import client_server_communication.client_pkg.ClientConnectionHandler;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) throws Exception {

        try {
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter user id: ");
            String user_id = scan.nextLine();
            System.out.println("Enter IP: ");
            String user_ip = scan.nextLine();
            System.out.println("Enter port:");
            String user_port = scan.nextLine();



        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
