package testing.server_pkg;

import client_server_communication.server_pkg.ServerUtils;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ServerUtilsTest {

    @Test
    public void test_console_outputs() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ServerUtils.console_output(1, "brm-Hello");
        assertTrue(outContent.toString().contains("Broadcast from 1: Hello"));

        outContent.reset();

        ServerUtils.console_output(1, "dm-2-Hello");
        assertTrue(outContent.toString().contains("Direct message from 1 to 2: Hello"));

        outContent.reset();

        ServerUtils.console_output(1, "exit");
        assertTrue(outContent.toString().contains("User 1 will now be disconnected..."));

        outContent.reset();

        ServerUtils.console_output(1, "act-grp-details");
        assertTrue(outContent.toString().contains("Coordinator requested active group details"));

        outContent.reset();

        ServerUtils.console_output(1, "grp-details");
        assertTrue(outContent.toString().contains("User 1 requested group details"));

        outContent.reset();

        ServerUtils.console_output(1, "random");
        assertTrue(outContent.toString().contains("RECEIVED MESSAGE: random"));
    }

}
