package peerlib;

import java.net.InetAddress;

public interface PeersManager {
    Iterable<InetAddress> getPeers();
}
