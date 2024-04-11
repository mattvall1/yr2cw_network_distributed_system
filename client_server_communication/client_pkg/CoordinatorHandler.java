package client_server_communication.client_pkg;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;


public class CoordinatorHandler implements Runnable {

    private final PrintWriter out;

    public CoordinatorHandler(PrintWriter out) {
        this.out = out;
    }

    private void send_check_request() {
        while(true) {
            try {
                // Wait for 20 seconds and send a message to receive details
                TimeUnit.SECONDS.sleep(20);
                System.out.println("Requesting group details...");
                this.out.println("act-grp-details");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void run() {
        // If we are the coordinator, continually check active group member details every 20 seconds
        send_check_request();
    }
}
