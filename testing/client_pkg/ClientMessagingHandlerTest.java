package testing.client_pkg;

import client_server_communication.client_pkg.ClientMessagingHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.util.ReflectionTestUtils;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientMessagingHandlerTest {

    @Mock
    private BufferedReader in;

    @Mock
    private PrintWriter out;

    @Mock
    private Socket socket;

    @Mock
    private Scanner scanner;

    @Test // THIS DOESNT WORK
    public void test_send_user_id() throws IOException {
        // Redirect standard output to capture printed messages
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create a mock client IDs set
        Set<Integer> client_ids_set = new HashSet<>();
        client_ids_set.add(1);
        client_ids_set.add(2);

        // Instantiate the ClientMessagingHandler (replace with actual instantiation if needed)
        ClientMessagingHandler clientMessagingHandler = new ClientMessagingHandler(in, out, socket);
        clientMessagingHandler.client_ids_set = client_ids_set;
        clientMessagingHandler.scan = scanner;
        ReflectionTestUtils.setField(clientMessagingHandler, "id_to_check", "3");

        when(in.readLine()).thenReturn("[1, 2, 3]", "3");

        // Call the method to test
        clientMessagingHandler.send_user_id();

        // Check if the expected message is printed
        String console_out = outputStream.toString().trim();
        assertTrue(console_out.contains("Your ID is already taken. Try again."));

        // Restore standard output
        System.setOut(System.out);
    }

    @Test
    public void test_check_message_validity() {
        ClientMessagingHandler clientMessagingHandler = new ClientMessagingHandler(in, out, socket);
        // Add some client IDs
        clientMessagingHandler.client_ids_set.add(1);
        clientMessagingHandler.client_ids_set.add(2);
        clientMessagingHandler.client_ids_set.add(3);
        clientMessagingHandler.client_ids_set.add(4);

        assertTrue(clientMessagingHandler.check_message_validity("brm-testa"));
        assertTrue(clientMessagingHandler.check_message_validity("brm-This is a sentence with some dashes-in-it-"));
        assertTrue(clientMessagingHandler.check_message_validity("dm-1-test"));
        assertTrue(clientMessagingHandler.check_message_validity("dm-1-This is a sentence with some dashes-in-it-"));
        assertTrue(clientMessagingHandler.check_message_validity("grp-details"));
        assertTrue(clientMessagingHandler.check_message_validity("exit"));
        assertFalse(clientMessagingHandler.check_message_validity("invalid message to send"));
    }
}
