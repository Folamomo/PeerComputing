package userinterface;

import computinglib.TaskManager;
import computinglib.TaskRepository;
import computinglib.Task_Primes;
import peerlib.CurrentPeersManager;

public class ResultShower extends Action {
    public ResultShower(TaskManager manager) {
        this.manager = manager;
    }

    private TaskManager manager;

    @Override
    void doAction() {

        TaskRepository rep = manager.getRepository();
        int id = 0;
        System.out.print("Results:\n");

        while((Task_Primes)rep.getTask(id).isDone()){
            System.out.print(rep.getTask(id).result + ", ");
            id ++;
        }



    }
}
