package client_server_communication.client_pkg;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.List;

public class ServerConnection {
    public static void connectToServer(String host, int port, int userID) throws IOException {
        /* Create client socket */
        Socket socket = new Socket(host, port);
        System.out.println("Connected to server");
        // Stream to send data to server
        DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());

        // Send user ID to server
        dataOutput.writeInt(userID);
        dataOutput.flush();

        // BufferedReader to read data from the keyboard
        BufferedReader kb
                = new BufferedReader(
                new InputStreamReader(System.in));

        Integer menu_option = 0;
        List<Integer> menu_options = Arrays.asList(1, 2, 3, 4);

        while(menu_option == 0 && !menu_options.contains(menu_option)) {
            // Menu system
            System.out.println("---- Main menu ----");
            System.out.println("1. Group chat");
            System.out.println("2. Direct messages");
            System.out.println("3. Main menu");
            System.out.println("4. Close/Leave");

            while(!menu_options.contains(menu_option)) {
                menu_option = Integer.parseInt(kb.readLine());
            }

            // Group chat functionality
            while (menu_option == 1) {
                String messageToSend;
                // Repeat if exit is not typed
                System.out.println("Input message: ");
                while (!(messageToSend = kb.readLine()).equals("exit")) {
                    System.out.println("Input message: ");
                    // Send to server
                    dataOutput.writeBytes(messageToSend + "\n");
                }
                menu_option = 0;
            }

            // TODO: DM functionality
            while (menu_option == 2) {
                System.out.println("Input user ID to send message to: ");
                String user_to_message = kb.readLine();
                System.out.println("ID entered: " + user_to_message);

                // TODO: Check if valid user ID
                // Send request to server to message other user
                dataOutput.writeBytes("direct_message_"+user_to_message);
                dataOutput.flush();

                System.exit(0);
                menu_option = 0;
            }

            // Makes more sense to user to have menu item for 'Main menu', so we set to 0 immediately
            if(menu_option == 3) {
                menu_option = 0;
            }

            // Exit program
            if(menu_option == 4) {
                System.out.println("Closing connection");
                // Close connection
                dataOutput.close();
                kb.close();
                socket.close();
                System.out.println("Success! Goodbye.");
            }
        }

    }
}
