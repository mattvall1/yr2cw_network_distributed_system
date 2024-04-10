package testing.client_pkg;

import client_server_communication.client_pkg.ClientMessagingHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientMessagingHandlerTest {

    @Mock
    private BufferedReader in;

    @Mock
    private PrintWriter out;

    @Mock
    private Socket socket;

    @Test
    public void test_send_user_id() throws IOException {
        when(in.readLine()).thenReturn("client-ids-[1, 2, 3]");
        ClientMessagingHandler clientMessagingHandler = new ClientMessagingHandler(in, out, socket);
        clientMessagingHandler.send_user_id();
        verify(out).println(anyInt());
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
