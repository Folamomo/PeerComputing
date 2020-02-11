package peerlibremastered;

import peerlib.MessageType;

import java.io.IOException;
import java.net.Socket;

public class MessageHandler {
    private Message message;
    private PeerClient peerClient;

    MessageHandler(Message message, Integer port, Socket socket){
        System.out.print("Created Message Handler for message from socket:" + socket.getInetAddress().getHostAddress() + "\n");
        this.message = message;
        this.peerClient = new PeerClient(port, socket);
    }


    public void handle() throws IOException {
        System.out.print("\nHandling " + this.message.type + "\n");
        switch (this.message.type) {
            case ERROR:
                throw new RuntimeException();
            case HAND:
                Message shake = new Message(MessageType.SHAKE, null);
                peerClient.sendMessage(shake);
                break;
            case SHAKE:
                break;
            case PING:
                break;
            case PONG:
                break;
            case GET_PEERS:
                break;
            case PEERS:
                break;
            case DATA:
                break;
            default:
        }

    }

}