package peerlibremastered;

import peerlib.MessageType;

import java.io.Serializable;

public class Message implements Serializable{
    public Long from;
    public Long to;
    public MessageType type;
    public Serializable payload;

    public Message(MessageType type, Serializable payload){
        this.from = from;
        this.to = to;
        this.type = type;
        this.payload = payload;
    }
}
