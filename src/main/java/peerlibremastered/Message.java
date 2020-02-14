package peerlibremastered;

import java.io.Serializable;

public class Message implements Serializable{
    public void setFrom(Integer from) {
        this.from = from;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public void setPayload(Serializable payload) {
        this.payload = payload;
    }

    public Integer from;
    public String adress;
    public MessageType type;
    public Serializable payload;

    public Message(Integer from, String address, MessageType type, Serializable payload) {
        this.from = from;
        this.type = type;
        this.payload = payload;
        this.adress = address;
    }


    public Message(MessageType type, Serializable payload){
        this.type = type;
        this.payload = payload;
    }
    
    
    
}
