package client_server_communication.client_pkg;
// Import packages

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientMessaging implements Runnable {
    public PrintWriter out;
    public BufferedReader in;
    public Socket socket;
    private final Scanner scan = new Scanner(System.in);
    public Integer user_id;

    public ClientMessaging(BufferedReader in, PrintWriter out, Socket socket) {
        this.out = out;
        this.in = in;
        this.socket = socket;
    }


    // Reading incoming data
    private void read() {
        try {
            String in_message = this.in.readLine();
            while (!in_message.equals("exit")){
                System.out.println(in_message);
                in_message = this.in.readLine();
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // Send outgoing data
    public void send() {
        String message = "";
        // Continual loop for user input

        while (!message.equals("exit")) {
            // Get input then send out to server
            System.out.println("Input message: ");
            message = scan.nextLine();
            this.out.println(message);

        }

    }

    // Get user id from users and send to server
    public void send_user_details() {
        System.out.println("Enter user id: ");
        // TODO: Check if input is valid
        user_id = Integer.parseInt(scan.nextLine());
        // Send to server
        this.out.println(user_id);
    }

    @Override
    public void run() {
        read();
    }
}
