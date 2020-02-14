package application;


import computinglib.TaskManager;
import computinglib.TaskRepository;
import peerlib.CurrentPeersManager;
import peerlib.MessageType;
import peerlib.PeerFacade;
import peerlibremastered.*;
import userinterface.UserInterface;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Integer appPort = 1112;
        String appAddress = "192.168.100.1";

        ConnectionMenager connectionMenager = new ConnectionMenager(appPort, appAddress);
        PeerServer server1 = new PeerServer(appPort, connectionMenager);
        Thread app1 = new Thread(server1);
        app1.start();

        connectionMenager.keepConnectionsStatus(6);

        UserInterface userInterface = new UserInterface(connectionMenager);
        Thread userInterfaceThread = new Thread(userInterface);
        userInterfaceThread.start();


//        ArrayList<Connection> connections2 = new ArrayList<Connection>();
//        connections2.add(new Connection("localhost", 2222));
//
//        ConnectionMenager cm2 = new ConnectionMenager(3333, connections2);
//
//        PeerServer server1 = new PeerServer(3333, cm2);
//
//        Thread app1 = new Thread(server1);
//
//        app1.start();
//
//        cm2.keepConnectionsStatus(6);
//
//        TimeUnit.SECONDS.sleep(10);
//        System.out.print("10s");
//        TimeUnit.SECONDS.sleep(10);
//
//        cm2.sendToAll(new Message(MessageType.HAND, null));


//---------------------------------------------------------

//        PeerServer server2 = new PeerServer(2222);
//        PeerServer server3 = new PeerServer(3333);
//        Thread app2 = new Thread(server2);
//        Thread app3 = new Thread(server3);
//        app2.start();
//        app3.start();
//
//        ArrayList<Connection> connections1 = new ArrayList<Connection>();
//        connections1.add(new Connection("localhost", 2222));
//        connections1.add(new Connection("localhost", 3333));
//
//        ArrayList<Connection> connections3 = new ArrayList<Connection>();
//        connections3.add(new Connection("localhost", 2222));
//        connections3.add(new Connection("localhost", 1111));
//
//        ConnectionMenager cm1 = new ConnectionMenager(1111, connections1);
//        cm1.sendToAll(new Message(MessageType.HAND, null));










        //cm3.sendToAll(new Message(MessageType.HAND, null));


//        TaskRepository taskRepository = new TaskRepository();
///
//        PeerFacade peerFacade = new PeerFacade();
//        CurrentPeersManager currentPeersManager = null;
//        try {
//            currentPeersManager = new CurrentPeersManager();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        TaskManager taskManager = new TaskManager(taskRepository, peerFacade);
//
//        UserInterface userInterface = new UserInterface(peerFacade, currentPeersManager, taskManager);





    }
}