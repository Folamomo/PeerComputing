package peerlib;

import computinglib.TaskManager;

import java.io.Serializable;

public interface Message {
    Peer getSender();

    void setSender();

    void send(Peer peer);

    Serializable decode();

    void handle(TaskManager taskManager);
}
