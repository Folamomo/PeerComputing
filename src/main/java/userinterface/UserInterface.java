package userinterface;
import computinglib.TaskManager;
import peerlibremastered.ConnectionMenager;

import java.util.Scanner;

public class UserInterface implements Runnable {


    private ConnectionMenager connectionMenager;
    private TaskManager tm;

    public UserInterface(ConnectionMenager connectionMenager, TaskManager tm){
        this.connectionMenager = connectionMenager;
        this.tm = tm;
    }

    public UserInterface(ConnectionMenager connectionMenager) {
        this.connectionMenager = connectionMenager;
    }


    @Override
    public void run() {
        boolean done = false;
        Scanner input = new Scanner(System.in);

        while (!done) {
            System.out.print("Press 0 to connect to peer\nPress 1 for new task\n Press 2 for seeing how many peers are available" +
                    "\n Press 3 for seeing actual result\n Press 4 to quit.\nEnter command: ");
            int number = input.nextInt();
            Action action = null;
            boolean legit_choice = false;
            switch (number){
                case 0:
                    action = new ConnectAction(connectionMenager);
                case 1:
                    //action = new TaskBuilder(this.peers, this.tm);
                    legit_choice = true;
                    break;
                case 2:
                    action = new PeersShower(this.connectionMenager);
                    legit_choice = true;
                    break;
                case 3:
                    action = new ResultShower(this.tm);
                    legit_choice = true;
                    break;
                case 4:
                    done = true;
            }
            if (legit_choice){
                action.doAction();
            }
        }
    }

    public static void main(String[] args){
        //UserInterface ui = new UserInterface();
        //ui.run();
    }
}
