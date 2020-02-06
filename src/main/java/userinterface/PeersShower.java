package userinterface;

import peerlib.CurrentPeersManager;
import peerlib.PeerFacade;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;

public class PeersShower extends Action {
    private CurrentPeersManager current;

    public PeersShower(CurrentPeersManager current) {
        this.current = current;
    }

    @Override
    void doAction() {
        System.out.print("Current peers: \n");
        Iterable<InetAddress> peers = this.current.getPeers();

        for(InetAddress adr : peers){
            String hostIP = adr.getHostAddress() ;
            String hostName = adr.getHostName();
            System.out.println( "IP: " + hostIP + "\n" + "Name: " + hostName + "\n");
        }

    }
}
