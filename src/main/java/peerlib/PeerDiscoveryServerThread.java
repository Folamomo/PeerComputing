package peerlib;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class PeerDiscoveryServerThread extends Thread {
    private DatagramSocket socket;
    private String message;
    private int port;

    public PeerDiscoveryServerThread() throws SocketException {
        super();
        port = 8880;
        socket = new DatagramSocket(port);
        message = "I'm alive";
    }

    public void run(ArrayList<Socket> sockets) throws IOException {
        while (true) {
            try {
                byte[] buf = new byte[256];
                buf = message.getBytes();

                InetAddress broadcastGroup = InetAddress.getByName("192.168.8.255"); // TODO wykrywanie broadcastu w sieci, w której jest peer
                DatagramPacket packet = new DatagramPacket(buf, buf.length, broadcastGroup, port);
                socket.send(packet);
            } catch (IOException e) {
                throw new IOException(e);
            } finally {
                socket.close();     //TODO ??????
            }
        }
    }
}
