package testing.server_pkg;

import client_server_communication.server_pkg.UserDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserDetailsTest {

    @Mock
    Socket socket;

    @Test
    public void test_user_details_constructor() {
        UserDetails userDetails = new UserDetails(socket, true);
        assertEquals(socket, userDetails.socket);
        assertTrue(userDetails.is_coordinator);
    }
}
