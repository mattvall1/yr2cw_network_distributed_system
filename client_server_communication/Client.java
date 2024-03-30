package client_server_communication;
// Import packages
import client_server_communication.client_pkg.ClientConnectionHandler;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) throws Exception {

        try {
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter IP: ");
            String user_ip = scan.nextLine();
            System.out.println("Enter port:");
            String user_port = scan.nextLine();


            Socket client_socket = new Socket(user_ip, Integer.parseInt(user_port));
            new Thread(new ClientConnectionHandler(client_socket)).start();


        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
