package testing.client_pkg;

import client_server_communication.client_pkg.ClientUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ClientUtilsTest {

    @Test
    public void test_initial_menu() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        ClientUtils.initial_menu(1);

        String expectedOutput  = "Welcome 1! Please read the following information before continuing\n" +
                "To send a broadcast message, start with: 'brm-'...\n" +
                "To send a direct message, start with: 'dm-USERIDHERE-'...\n" +
                "To get details of all group members, enter 'grp-details'\n" +
                "To exit/leave the chat program, press ctrl+C\n";

        assertEquals(expectedOutput, out.toString());
    }

    @Test
    public void test_get_current_datetime() {
        String currentDatetime = ClientUtils.get_current_datetime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String expectedDatetime = now.format(formatter);

        assertTrue(currentDatetime.startsWith(expectedDatetime.substring(0, expectedDatetime.length() - 2)));
    }
}
