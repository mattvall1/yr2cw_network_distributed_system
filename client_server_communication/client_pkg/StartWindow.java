package client_server_communication.client_pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow {
    private static String userID;
    private static String ip;
    private static String port;

    public static void main() {
        // Create the main frame
        JFrame frame = new JFrame("Client Configuration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create a panel with a grid layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        // Create labels and text fields
        JLabel userIDLabel = new JLabel("User ID:");
        JTextField userIDField = new JTextField();

        JLabel serverIPLabel = new JLabel("Server IP:");
        JTextField serverIPField = new JTextField();

        JLabel serverPortLabel = new JLabel("Server Port:");
        JTextField serverPortField = new JTextField();

        // Create the submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the values from the text fields
                userID = userIDField.getText();
                ip = serverIPField.getText();
                port = serverPortField.getText();

                // TEMP - Set IP and port every time
                ip = "localhost";
                port = String.valueOf(850);

                // You can use these values to send data to the server
                System.out.println("User ID: " + userID);
                System.out.println("Server IP: " + ip);
                System.out.println("Server Port: " + port);

                // Close the window
                frame.dispose();
            }
        });

        // Add components to the panel
        panel.add(userIDLabel);
        panel.add(userIDField);
        panel.add(serverIPLabel);
        panel.add(serverIPField);
        panel.add(serverPortLabel);
        panel.add(serverPortField);
        panel.add(submitButton);

        // Add the panel to the frame and make it visible
        frame.add(panel);
        frame.setVisible(true);
    }
}
