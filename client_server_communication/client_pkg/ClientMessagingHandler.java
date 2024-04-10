package client_server_communication.client_pkg;
// Import packages

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class ClientMessagingHandler implements Runnable  {
    public PrintWriter out;
    public BufferedReader in;
    public Socket socket;
    public Scanner scan = new Scanner(System.in);
    public Integer user_id;
    public Boolean is_coordinator;
    public Set<Integer> client_ids_set = new HashSet<>();

    public ClientMessagingHandler(BufferedReader in, PrintWriter out, Socket socket) {
        this.out = out;
        this.in = in;
        this.socket = socket;
    }

    // Choose read script (multithreading if we are coordinator/normal read if not)
    private void choose_read_script() throws InterruptedException, IOException {
        // Second received message is always coordinator check
        String in_message = this.in.readLine();

        if(in_message.matches("^coord$")) {
            System.out.println("You are the coordinator, you will receive group information every 20 seconds.");
            is_coordinator = true;
            // If we become the coordinator, we need to run multithreading
            coordinator_thread_handler();
        } else {
            read();
        }
    }


    // Reading incoming data
    private void read() {
        try {
            String in_message = this.in.readLine();
            while (!in_message.equals("exit")){
                if(in_message.matches("^coord$")) {
                    System.out.println("You are the coordinator, you will receive group information every 20 seconds.");
                    is_coordinator = true;
                    // If we become the coordinator, we need to kill this read script and run the multithreading script which includes coordinator details routine
                    break;
                }

                // General console outputting
                System.out.println(ClientUtils.get_current_datetime() + " | " + in_message);
                in_message = this.in.readLine();
            }

            // Run multithreaded coordinator routines if needed
            if(is_coordinator && !in_message.equals("exit")) coordinator_thread_handler();

        } catch(IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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

    // Get all current client ids
    private Set get_client_ids() throws IOException {
        String client_ids = this.in.readLine();
        client_ids = client_ids.replaceFirst("^client-ids-", "");

        // If the string isn't blank and includes some ids, convert to a set
        if(client_ids.matches("\\[\\d+(, \\d+)*\\]")) {
            client_ids_set = Arrays.stream(client_ids.replaceAll("[\\[\\]]", "").split(", ")).map(Integer::parseInt).collect(Collectors.toSet());
        }
        return client_ids_set;
    }

    // Get user id from users and send to server
    public void send_user_id() throws IOException {
        // Setup variables
        client_ids_set = get_client_ids();
        Boolean valid_id = false;
        String id_to_check = "";

        System.out.println("The current list of client IDs is: " + client_ids_set);
        while(!valid_id) {
            System.out.println("Enter user id: ");
            id_to_check = scan.nextLine();
            // First, check it's an integer
            if(id_to_check.matches("\\d+")) {
                Integer id_to_check_int = Integer.parseInt(id_to_check);
                // Next, check it isn't already taken by another client
                if(!client_ids_set.contains(id_to_check_int)) {
                    valid_id = true;
                } else {
                    System.out.println("Your ID is already taken. Try again.");
                }
            } else {
                System.out.println("Your ID must be numerical only. Try again.");
            }
        }
        user_id = Integer.parseInt(id_to_check);

        // Send to server
        this.out.println(user_id);
    }

    // Function to make message readable by server
    public Boolean check_message_validity(String message) {
        if(message.matches("^brm-.*")) {
            return true;
        } else if(message.matches("^dm-\\d+-.*")) {
            int send_to = Integer.parseInt(message.substring(3, message.indexOf("-", 3)));
            if(client_ids_set.contains(send_to)) {
                return true;
            } else {
                System.out.println("This user does not exist.");
            }
        } else if(message.matches("^grp-details$")) {
            // Tell the user we are getting group details in this case
            System.out.println("Full group details:");
            return true;
        } else if(message.matches("^exit$")) {
            // Tell the user we are getting group details in this case
            System.out.println("Sending exit request to server...");
            return true;
        }
        return false;
    }

    // If we are a coordinator, we need to run two threads, one for the continuous read and one for coordinator requests
    private void coordinator_thread_handler() throws InterruptedException {
        // Setup threads
        Thread reader = new Thread() {
            public void run() {
                // Run read scripts
                read();
            }
        };

        Thread coordinator_request_handler = new Thread() {
            public void run() {
                // Get coordinator handler
                CoordinatorHandler coordinatorHandler = new CoordinatorHandler(out);
                coordinatorHandler.run();
            }
        };

        // Run coordinator handler alongside read script
        reader.start();
        coordinator_request_handler.start();

        // Close threads when they're no longer needed
        reader.join();
        coordinator_request_handler.join();
    }

    @Override
    public void run() {
        try {
            choose_read_script();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
