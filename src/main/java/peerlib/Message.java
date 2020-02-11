package peerlib;

import java.io.Serializable;

public class Message implements Serializable{
    public Long from;
    public Long to;
    public MessageType type;
    public Serializable payload;

    Message(Long from, Long to, MessageType type, Serializable payload){
        this.from = from;
        this.to = to;
        this.type = type;
        this.payload = payload;
    }
}
