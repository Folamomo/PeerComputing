package peerlibremastered;

import org.junit.Test;
import peerlib.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class PeerServerTest {

    @Test
    public void runTest() throws IOException, InterruptedException, ClassNotFoundException {

        PeerServer myServer = new PeerServer(11113);
        Thread server = new Thread(myServer);
        server.start();

        int portNum = 11113;

        Socket socket = new Socket("127.0.0.1", portNum);

        // Integer Object to send to Server.
        Message num = new Message( MessageType.SHAKE, null);

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        //ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        out.writeObject(num);
        out.flush();
        //String response = (String) in.readObject();

        //System.out.println("Server message: " + response);
       // System.out.print("sleeping...\n");
       // TimeUnit.SECONDS.sleep(5);
        num = new Message( MessageType.HAND, null);
        //ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        out.writeObject(num);
        out.flush();


        TimeUnit.SECONDS.sleep(5);

        server.join();

    }

}