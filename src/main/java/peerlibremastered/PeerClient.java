package peerlibremastered;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PeerClient {
    public Integer port;
    public Socket socket;

    PeerClient(Integer port, Socket socket){
        System.out.print("Created PeerClient for port " + port + "\n");
        this.port = port;
        this.socket = socket;
    }

    public void sendMessage(Message message) throws IOException {
        System.out.print("Sending message " + message.type + " to " + socket.getInetAddress().getHostAddress() + " on port " + port + "\n");

        Socket socket = this.socket;
        // Integer Object to send to Server.

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(message);
        out.flush();
    }
}
