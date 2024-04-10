package testing.server_pkg;

import client_server_communication.server_pkg.ClientServerConnectionHelper;
import client_server_communication.server_pkg.UserDetails;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServerConnectionHelperTest {

    @Mock
    private Scanner scanner;

    @Mock
    private Socket socket;

    @Mock
    private UserDetails user_details;

    @Test
    public void test_disconnect_routine() throws IOException {
        // Build client details (Arrange)
        Integer user_id = 1;
        user_details = new UserDetails(socket, true);
        ClientServerConnectionHelper.client_details.put(user_id, user_details);

        // Run the routine (Act)
        ClientServerConnectionHelper.disconnect_routine(user_id, scanner);

        // Verify we've closed everything properly (Assert)
        verify(scanner).close();
        verify(socket).close();
    }
}
