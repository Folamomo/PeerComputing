package userinterface;

import peerlib.CurrentPeersManager;
import peerlibremastered.Connection;
import peerlibremastered.ConnectionMenager;

import java.net.InetAddress;

public class PeersShower extends Action {
    private ConnectionMenager current;

    public PeersShower(ConnectionMenager current) {
        this.current = current;
    }

    @Override
    void doAction() {
        System.out.print("\n\n\nCurrent peers: \n");
        for(Connection connection : current.connections){
            Integer port = connection.remoteHost;
            String adress = connection.remoteServer;
            System.out.print("Connection: " + adress + ", " + port + "\n");
        }

    }
}
