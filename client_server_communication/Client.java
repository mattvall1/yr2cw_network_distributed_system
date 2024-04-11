package client_server_communication;
// Import packages
import client_server_communication.client_pkg.ClientConnectionHandler;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) throws Exception {

        try {
            Scanner scan = new Scanner(System.in);

            // Check the user enters the server ip address '127.0.0.1' and port '850'
            String user_ip = "";
            String user_port = "";
            while (!user_ip.equals("127.0.0.1")) {
                System.out.println("Enter IP: ");
                user_ip = scan.nextLine();
                if(!user_ip.equals("127.0.0.1")) {
                    System.out.println("That was incorrect, did you input the correct IP?");
                }
            }
            while (!user_port.equals("850")) {
                System.out.println("Enter port: ");
                user_port = scan.nextLine();
                if(!user_port.equals("850")) {
                    System.out.println("That was incorrect, did you input the correct port?");
                }
            }


            Socket client_socket = new Socket(user_ip, Integer.parseInt(user_port));
            new Thread(new ClientConnectionHandler(client_socket)).start();


        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
