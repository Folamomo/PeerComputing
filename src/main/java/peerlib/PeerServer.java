package peerlib;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PeerServer implements Runnable {

    private ServerSocket serverSocket;

    public PeerServer() throws IOException {
        this.serverSocket = new ServerSocket(12345);
    }

    @Override
    public void run() {
        try {
            Socket accept = serverSocket.accept();
            new Peer(accept);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
