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

        PeerServer server1 = new PeerServer(1111);
        PeerServer server2 = new PeerServer(2222);
        Thread app1 = new Thread(server1);
        Thread app2 = new Thread(server2);
        app1.start();
        app2.start();

        ArrayList<Connection> connections = new ArrayList<Connection>();
        connections.add(new Connection("localhost", 1111));
        ArrayList<Connection> connections2 = new ArrayList<Connection>();
        connections2.add(new Connection("localhost", 2222));

        TimeUnit.SECONDS.sleep(5);

        ConnectionMenager cm1 = new ConnectionMenager(1111, connections2);
        ConnectionMenager cm2 = new ConnectionMenager(2222, connections);

        cm1.sendToAll(new Message(MessageType.HAND, null));



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