package peerlib;

import java.net.Socket;

public interface PeersManager {
    Iterable<Socket> getPeers();
}
