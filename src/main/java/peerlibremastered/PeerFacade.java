package peerlibremastered;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PeerFacade {

    private Collection<Message> messages = new ConcurrentLinkedQueue<>();
    public ConnectionMenager connectionMenager;

    public PeerFacade(ConnectionMenager connectionMenager) {
        this.connectionMenager = connectionMenager;
    }

    public void sendToAll(Message message){
        try {
            connectionMenager.sendToAll(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendToSpecific(Message message, Connection connection) {
        connectionMenager.sendToSpecific(message, connection);
    }
    public Collection<Message> getAllMessages() {
        Collection<Message> result = messages;
        messages = new ConcurrentLinkedQueue<>();
        return result;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
