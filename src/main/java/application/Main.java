package application;


import computinglib.TaskManager;
import computinglib.TaskRepository;
import peerlib.CurrentPeersManager;
import peerlib.PeerFacade;
import userinterface.UserInterface;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        TaskRepository taskRepository = new TaskRepository();

        PeerFacade peerFacade = new PeerFacade();
        CurrentPeersManager currentPeersManager = null;
        try {
            currentPeersManager = new CurrentPeersManager();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TaskManager taskManager = new TaskManager(taskRepository, peerFacade);

        UserInterface userInterface = new UserInterface(peerFacade, currentPeersManager, taskManager);

    }
}