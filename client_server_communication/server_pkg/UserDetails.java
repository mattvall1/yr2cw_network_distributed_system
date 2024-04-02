package client_server_communication.server_pkg;
import java.net.Socket;

// UserDetails class for adding coordinator details
public class UserDetails {
    Socket socket;
    boolean is_coordinator;

    public UserDetails(Socket socket, boolean is_coordinator) {
        this.socket = socket;
        this.is_coordinator = is_coordinator;
    }

}