package peerlib;

import org.junit.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import static org.junit.Assert.*;

public class PeerServerTest {

    @Test
    public void runTest() throws IOException, InterruptedException, ClassNotFoundException {

        PeerServer myServer = new PeerServer();
        Thread server = new Thread(myServer);
        server.start();


        int portNum = 11113;

        Socket socket = new Socket("localhost", portNum);

        // Integer Object to send to Server.
        Message num = new Message(1L, 1L, MessageType.HAND, null);

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        out.writeObject(num);

        String response = (String) in.readObject();

        System.out.println("Server message: " + response);


    }

}