package testing.client_pkg;

import client_server_communication.client_pkg.ClientConnectionHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClientConnectionHandlerTest {
    @Mock
    ClientConnectionHandler clientHandler;
    @Test
    void run() throws IOException {
        Thread thread = new Thread(clientHandler);
        thread.start();
        verify(clientHandler).run();
    }
}
