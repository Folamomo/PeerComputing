package peerlib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class PeerService implements Runnable {
    private List<Message> messages;
    private Map<Long, Peer> peers;
    private Peer myself;
    MessageServer messageServer;

    public PeerService() {
        messages = new ArrayList<>();
        peers = new HashMap<>();
        myself =
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

    }
}
