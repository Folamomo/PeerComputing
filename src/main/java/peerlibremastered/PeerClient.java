package peerlibremastered;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class PeerClient {
    public Integer port;
    public Socket socket;
    public ObjectOutputStream out;

    PeerClient(Integer port, Socket socket){
        System.out.print("Created PeerClient for port " + port + "\n");
        this.port = port;
        this.socket = socket;
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message) throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.print("Sending message " + message.type + " to " + socket.getInetAddress().getHostAddress() + " on port " + port + "\n");

        Socket socket = this.socket;
        // Integer Object to send to Server.

        ObjectOutputStream out = this.out;

        out.writeObject(message);
        out.flush();
    }
}
