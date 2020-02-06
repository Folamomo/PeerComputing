package peerlib;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class PeerDiscoveryClient extends Thread {
    private MulticastSocket socket;
    private String message;
    private int port;

    public PeerDiscoveryClient() throws IOException {
        super();
        port = 8880;
        socket = new MulticastSocket(port);
        message = "Are you alive?";
    }

    public void run(Map<InetAddress, Date> peerList) throws IOException {
        InetAddress groupAddress = InetAddress.getByName("192.168.8.255");
        socket.joinGroup(groupAddress);
        while (true) {
            try {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                // TODO obsłużyć otrzymaną wiadomość - zapisać adres/socket w tablicy peerów
                InetAddress address = packet.getAddress();

                Date result = peerList.get(address);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                if (result == null) {
                    peerList.put(address, date);
                } else {
                    peerList.replace(address, date);
                }
                for (Map.Entry<InetAddress, Date> entry : peerList.entrySet()) {
                    if ((date.getTime() - entry.getValue().getTime())/1000 > 60) {
                        peerList.remove(entry.getKey());
                    }
                }
            } catch (IOException e) {
                throw new IOException(e);
            }
        }
        //socket.leaveGroup(groupAddress);
        //socket.close();
    }
}
