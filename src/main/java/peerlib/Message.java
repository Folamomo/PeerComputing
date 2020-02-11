package peerlib;

import java.io.Serializable;

public class Message {
    Long from;
    Long to;
    MessageType type;
    Serializable payload;
}
