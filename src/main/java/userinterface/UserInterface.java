package userinterface;
import computinglib.TaskManager;
import peerlib.CurrentPeersManager;
import peerlib.PeerFacade;

import java.util.Scanner;

public class UserInterface implements Runnable {
    private PeerFacade peers;
    private CurrentPeersManager current;
    private TaskManager tm;

    public UserInterface(PeerFacade peers, CurrentPeersManager current, TaskManager tm){
        this.peers = peers;
        this.current = current;
        this.tm = tm;
    }

    @Override
    public void run() {
        boolean done = false;
        Scanner input = new Scanner(System.in);

        while (!done) {
            System.out.print("Press 1 for new task\n Press 2 for seeing how many peers are available" +
                    "\n Press 3 for seeing actual result\n Press 4 to quit.\nEnter command: ");
            int number = input.nextInt();
            Action action = null;
            boolean legit_choice = false;
            switch (number){
                case 1:
                    action = new TaskBuilder(this.peers, this.tm);
                    legit_choice = true;
                    break;
                case 2:
                    action = new PeersShower(this.current);
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
