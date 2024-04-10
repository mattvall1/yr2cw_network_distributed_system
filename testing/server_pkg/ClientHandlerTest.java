package testing.server_pkg;

import client_server_communication.server_pkg.ClientHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientHandlerTest {
    @Mock
    ClientHandler clientHandler;
    @Test
    void run() throws IOException {
        Thread thread = new Thread(clientHandler);
        thread.start();
        verify(clientHandler).run();
    }
}

