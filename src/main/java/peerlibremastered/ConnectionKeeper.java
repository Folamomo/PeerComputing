package peerlibremastered;

import peerlib.MessageType;

import java.util.concurrent.TimeUnit;

public class ConnectionKeeper implements Runnable {

    private ConnectionMenager connectionMenager;
    private Integer interval;

    public ConnectionKeeper(ConnectionMenager connectionMenager, Integer intervalSeconds) {
        this.connectionMenager = connectionMenager;
        this.interval = intervalSeconds;
    }


    @Override
    public void run() {
        for (;;){
            try {
                connectionMenager.sendToAll(new Message(connectionMenager.serverPort, connectionMenager.serverAddress, MessageType.HAND,null));
                TimeUnit.SECONDS.sleep(this.interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
