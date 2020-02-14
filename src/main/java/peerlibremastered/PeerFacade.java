package peerlibremastered;

import java.util.ArrayList;
import java.util.List;

public class PeerFacade {

    private List<Message> messages;
    private ConnectionMenager connectionMenager;

    public void sendToAll(Message message){
        try {
            connectionMenager.sendToAll(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Message> getAllMessages() {
        List<Message> result = messages;
        messages = new ArrayList<>();
        return result;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
