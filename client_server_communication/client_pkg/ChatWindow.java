package client_server_communication.client_pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow {
    JFrame frame;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChatWindow window = new ChatWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ChatWindow() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        chatArea = new JTextArea();
        chatArea.setBounds(10, 11, 414, 201);
        frame.getContentPane().add(chatArea);

        messageField = new JTextField();
        messageField.setBounds(10, 223, 314, 20);
        frame.getContentPane().add(messageField);
        messageField.setColumns(10);

        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Your BufferedReader code can go here
                // For now, it just appends the text to the chatArea
                chatArea.append(messageField.getText() + "\n");
                messageField.setText("");
            }
        });
        sendButton.setBounds(334, 222, 89, 23);
        frame.getContentPane().add(sendButton);
    }
}
