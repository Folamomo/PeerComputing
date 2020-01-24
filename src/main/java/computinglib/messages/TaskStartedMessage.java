package computinglib.messages;

import computinglib.Task;
import peerlib.Message;
import peerlib.Peer;

import java.io.Serializable;

public class TaskStartedMessage implements Message {
    public TaskStartedMessage(Task<?> task) {

    }

    @Override
    public void send(Peer peer) {

    }

    @Override
    public Serializable decode() {
        return null;
    }
}
