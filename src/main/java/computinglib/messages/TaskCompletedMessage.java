package computinglib.messages;

import computinglib.Task;
import peerlib.Message;
import peerlib.Peer;

import java.io.Serializable;

public class TaskCompletedMessage implements Message {
    public TaskCompletedMessage(Task<?> task) {

    }

    @Override
    public void send(Peer peer) {

    }

    @Override
    public Serializable decode() {

    }
}
