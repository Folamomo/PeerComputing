package peerlibremastered;

import peerlib.MessageType;

import java.net.Socket;

public class MessageHandler implements Runnable {
    private Message message;
    private PeerClient peerClient;
    private Integer myPort;
    private ConnectionMenager connectionMenager;
    private Integer port;

    MessageHandler(Message message, Integer port, Integer fromPort, Socket socket, ConnectionMenager connectionMenager){
        //System.out.print("Created Message Handler for message from socket:" + socket.getInetAddress().getHostAddress() + "\n");
        this.message = message;
        this.peerClient = new PeerClient(port, socket);
        this.myPort = fromPort;
        this.connectionMenager = connectionMenager;
        this.port = port;
    }

    @Override
    public void run() {
        System.out.print("\nHandling " + this.message.type + "\n");
        switch (this.message.type) {
            case ERROR:
                throw new RuntimeException();
            case HAND:
                Message shake = new Message(myPort, MessageType.SHAKE, null);
                connectionMenager.addIfNew(new Connection("localhost", port)); //TODO zmienic localhosta na co innego
                try {
                    peerClient.sendMessage(shake);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case SHAKE:
                System.out.print("Message SHAKE handled");
                break;
            case PEERS:
                break;
            case DATA:
                break;
            case NEW_PEER_ALERT:
                Connection connection = (Connection) message.payload;
                connectionMenager.addIfNew(connection);
                break;
            default:
        }

    }

}
