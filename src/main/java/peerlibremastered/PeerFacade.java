package peerlibremastered;

import java.util.ArrayList;
import java.util.List;

public class PeerFacade {

    private List<Message> messages;
    ConnectionMenager connectionMenager;

    public void sendToAll(Message message) throws InterruptedException {
        connectionMenager.sendToAll(message);
    }

    List<Message> getAllMessages() {
        List<Message> result = messages;
        messages = new ArrayList<>();
        return result;
    }

    void addMessage(Message message) {
        messages.add(message);
    }
}
