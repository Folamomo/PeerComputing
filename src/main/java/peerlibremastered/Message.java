package peerlibremastered;

import peerlib.MessageType;

import java.io.Serializable;

public class Message implements Serializable{
    public void setFrom(Integer from) {
        this.from = from;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public void setPayload(Serializable payload) {
        this.payload = payload;
    }

    public Integer from;
    public Integer to;
    public MessageType type;
    public Serializable payload;

    public Message(Integer from, MessageType type, Serializable payload) {
        this.from = from;
        this.type = type;
        this.payload = payload;
    }


    public Message(MessageType type, Serializable payload){
        this.type = type;
        this.payload = payload;
    }
    
    
    
}
