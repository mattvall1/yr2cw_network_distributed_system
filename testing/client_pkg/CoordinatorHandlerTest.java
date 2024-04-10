package testing.client_pkg;

import client_server_communication.client_pkg.CoordinatorHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintWriter;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CoordinatorHandlerTest {

    @Mock
    private PrintWriter out;

    // NOTE: This test is slow, let run in the background
    @Test
    public void test_send_check_request() throws InterruptedException {
        CoordinatorHandler coordinatorHandler = new CoordinatorHandler(out);

        // Run the CoordinatorHandler as a Thread
        Thread thread = new Thread(coordinatorHandler);
        thread.start();

        // Let the thread run for a while
        Thread.sleep(120000);

        // Check the request is sent a few times
        verify(out, times(5)).println("act-grp-details");

    }
}
