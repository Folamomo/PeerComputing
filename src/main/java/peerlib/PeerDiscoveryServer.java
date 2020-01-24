package peerlib;

import java.io.IOException;

public class PeerDiscoveryServer {
    public static void start() throws IOException {
        new PeerDiscoveryServerThread().start();
    }
}
