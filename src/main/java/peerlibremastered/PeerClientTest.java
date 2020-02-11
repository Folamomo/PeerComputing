package peerlibremastered;

import org.junit.Test;
import peerlib.MessageType;

import java.io.IOException;
import java.net.Socket;

public class PeerClientTest {
    @Test
    public void runTest() throws IOException, InterruptedException, ClassNotFoundException {
        PeerServer ps = new PeerServer(11113);
        Thread server = new Thread(ps);
        server.start();

        Socket socket = new Socket("127.0.0.1", 11113);

        PeerClient client = new PeerClient(11113, socket);

        Message num = new Message(MessageType.HAND, null);

        client.sendMessage(num);

        server.join();
    }

}