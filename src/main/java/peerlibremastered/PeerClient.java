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

    public PeerClient(Integer port, Socket socket){
        this.port = port;
        this.socket = socket;
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("Created PeerClient for port " + port + "\n");

    }

    public void sendMessage(Message message) throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.print("Sending message " + message.type + " to " + socket.getInetAddress().getHostAddress() + " on port " + port + "\n");

        // Integer Object to send to Server.

        ObjectOutputStream out = this.out;

        if (message.from == null){
            System.out.print("Message passed to PeerCLient has no from field. \n");
        }

        out.writeObject(message);


        //out.flush();
        System.out.print("Message " + message.type + " sent.\n");
    }
}
