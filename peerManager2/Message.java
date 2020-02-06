package peerlib;

import java.io.Serializable;

public abstract class Message {
    void encode(Serializable contents) {

    }

    void send(Peer peer) {
    }

    Serializable decode() {
        return null;
    }

    ;
}
