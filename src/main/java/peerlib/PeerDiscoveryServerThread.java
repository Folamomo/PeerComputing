package peerlib;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class PeerDiscoveryServerThread extends Thread {
    private DatagramSocket socket;
    private String message;
    private int port;
    private InetAddress broadcastGroup = null;

    public PeerDiscoveryServerThread() throws SocketException, BroadcastAddressException {
        super();
        port = 8880;
        socket = new DatagramSocket(port);
        message = "I'm alive";

        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            if (networkInterface.isLoopback())
                continue;    // Do not want to use the loopback interface.
            for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                InetAddress broadcastGroup = interfaceAddress.getBroadcast();
                if (broadcastGroup == null)
                    continue;
            }
        }
        if (broadcastGroup == null)
            throw new BroadcastAddressException("Cannot get broadcast address of the network");
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
            }
//            finally {
//                socket.close();
//            }
        }
    }
}
