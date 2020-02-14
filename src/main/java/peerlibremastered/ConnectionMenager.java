package peerlibremastered;

import peerlib.MessageType;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionMenager{
    public Integer serverPort;
    public String serverAddress;
    public List<Connection> connections;
    public PeerFacade peerFacade;

    public ConnectionMenager(Integer serverPort, String serverAddress) {
        this.serverPort = serverPort;
        this.connections = new ArrayList<>();
        this.serverAddress = serverAddress;
    }

    public ConnectionMenager(Integer serverPort, String serverAddress, PeerFacade peerFacade) {
        this.serverPort = serverPort;
        this.serverAddress = serverAddress;
        this.peerFacade = peerFacade;
    }

    public ConnectionMenager(Integer serverport, List<Connection> connections) {
        this.serverPort = serverport;
        this.connections = connections;
    }

    public void sendToSpecific(Message message, Connection connection) {
        try {
            new PeerClient(connection.remoteHost, new Socket(connection.remoteServer, connection.remoteHost)).sendMessage(message);
        } catch (InterruptedException | IOException e) {
            System.out.print("Could not send to specific: " + connection.remoteServer + ", " + connection.remoteHost + "\n" );
        }
    }

    public void sendToAll(Message message) throws InterruptedException {
        System.out.print("Sending to all\n");

        ArrayList<Connection> activeConnections = new ArrayList<>();

        for(Connection connection : connections){
            System.out.print("Sending to " + connection.remoteHost + "\n");
            if (message.from == null){
                message.setFrom(this.serverPort);
            }

            Socket s;
            try {
                s = new Socket(connection.remoteServer, connection.remoteHost);
            } catch (IOException e) {
                System.out.print("Could not establish connection with: " + connection.remoteServer + ", " + connection.remoteHost +
                        " removing from connection list. \n");
                saveMessage(new Message(connection.remoteHost, connection.remoteServer, MessageType.ERROR, null));
                continue;
            }

            PeerClient p = new PeerClient(connection.remoteHost, s );
            p.sendMessage(message);

            activeConnections.add(connection);

        }

        this.connections = activeConnections;

    }

    public void addIfNew(Connection newConnection){
        if (this.connections == null){
            this.connections.add(newConnection);
            System.out.print("Added new connection to connection list: " + newConnection.remoteHost + ", " + newConnection.remoteServer + "\n");
            saveMessage(new Message(newConnection.remoteHost, newConnection.remoteServer, MessageType.REQUEST_DATA, null));
            return;
        }

        if (this.connections.contains(newConnection)){
            System.out.print("This adress is already in our ocnnection list, no need to add\n");
            return;
        }
        System.out.print("Propagating new connection to all peers\n");

        //Sending new connection to current peers
        try {
            this.sendToAll(new Message(this.serverPort, this.serverAddress, MessageType.NEW_PEER_ALERT, newConnection));
        } catch (InterruptedException e) {
            System.out.print("AddIfNew - propagate EEEEEEEEEEEEEERRRRRRRRRRRRRRRRRRRRRRRRRRRROOOOOOOOOOORRRRRRRRR\n");
        }

        //Sending current connections to new peer

        for(Connection connection : connections) {
            this.sendToSpecific(new Message(this.serverPort, this.serverAddress, MessageType.NEW_PEER_ALERT, connection), newConnection);
        }

        this.connections.add(newConnection);
        System.out.print("Added new connection to connection list: " + newConnection.remoteHost + ", " + newConnection.remoteServer + "\n");

    }


    public void keepConnectionsStatus(Integer interval) {
        ConnectionKeeper connectionKeeper = new ConnectionKeeper(this, interval);
        new Thread(connectionKeeper).start();
    }

    public void saveMessage(Message message) {
        peerFacade.addMessage(message);
    }

}
