package userinterface;

import peerlibremastered.MessageType;
import peerlibremastered.Connection;
import peerlibremastered.ConnectionMenager;
import peerlibremastered.Message;

import java.util.Scanner;

public class ConnectAction extends Action {

    public ConnectAction(ConnectionMenager connectionMenager) {
        this.connectionMenager = connectionMenager;
    }

    private ConnectionMenager connectionMenager;

    @Override
    void doAction() {
        Scanner input = new Scanner(System.in);

        System.out.print("Provide adress: \n");
        String address = input.nextLine();
        System.out.print("Provide port: \n");
        int port = input.nextInt();

        this.connectionMenager.sendToSpecific(new Message(connectionMenager.serverPort, connectionMenager.serverAddress, MessageType.HAND,
                null), new Connection(address, port));
        this.connectionMenager.addIfNew(new Connection(address, port));
    }
}
