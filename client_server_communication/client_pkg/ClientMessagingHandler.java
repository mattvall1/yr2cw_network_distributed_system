package client_server_communication.client_pkg;
// Import packages

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ClientMessagingHandler implements Runnable {
    public PrintWriter out;
    public BufferedReader in;
    public Socket socket;
    private final Scanner scan = new Scanner(System.in);
    public Integer user_id;

    public ClientMessagingHandler(BufferedReader in, PrintWriter out, Socket socket) {
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
            message =scan.nextLine();
            if(check_message_validity(message)) {
                this.out.println(message);
            } else {
                System.out.println("Invalid message, did you start check message requirements?");
            }
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

    // Function to make message readable by server
    private Boolean check_message_validity(String message) {
        if(message.matches("^brm-.*")) {
            return true;
        } else if(message.matches("^dm-\\d+-.*")) {
            // TODO: Check if we have a valid user_id
            int send_to = Integer.parseInt(message.substring(3, message.indexOf("-", 3)));
            if(1 == 1) {
                return true;
            } else {
                return false;
            }

        } else if(message.matches("^grp-details$")) {
            // Tell the user we are getting group details in this case
            System.out.println("Full group details:");
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        read();
    }
}
