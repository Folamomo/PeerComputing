package peerlib;

import peerlibremastered.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;

import static peerlibremastered.MessageType.SHAKE;

public class PeerService implements Runnable {
    private List<Message> messages;
    private ConcurrentMap<Long, Peer> peers;
    private Peer me;

    public PeerService() {
        messages = new ArrayList<>();
        peers = new ConcurrentHashMap<>();
    }

    public void send(Message message) {

    }

    public void sendToAll(Message message) {

    }

    List<Message> getAllMessages() {
        List<Message> result = messages;
        messages = new ArrayList<>();
        return result;
    }

    List<Message> getMessagesBy(Predicate<Message> predicate) {
        List<Message> result = new ArrayList<>();
        for (Message message : messages) {
            if (predicate.test(message)) {
                result.add(message);
                messages.remove(message);
            }
        }
        return result;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (Peer peer : peers.values()) {
                    List<Message> messages = peer.receive();
                    for (Message message : messages) {
                        handleMessage(message);
                    }
                }
            }
       } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void handleMessage(Message message){
        switch (message.type) {
            case ERROR:
                throw new RuntimeException();
            case HAND:

                this.send(new Message(SHAKE, null));
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
