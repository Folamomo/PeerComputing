package peerlib;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;

public class CurrentPeersManager {
    //private ArrayList<Socket> peerList;
    private Map<InetAddress, Date> peerList;
    private InetAddress localInetAddress; //TODO zrobić z tego zasób współdzielony i operacje na nim w odpowiednich wątkach
    private PeerDiscoveryClient client;

    public CurrentPeersManager() throws IOException {
        try {
            localInetAddress = Inet4Address.getLocalHost();
            try {
                PeerDiscoveryServer.start();
            } catch (BroadcastAddressException e) {
                e.printStackTrace();
            }
            client = new PeerDiscoveryClient();
            client.run(peerList);
        } catch (UnknownHostException e) {
            throw new IOException(e);
        }
    }

    public Iterable<InetAddress> getPeers() {
        return peerList.keySet();
    }
}
