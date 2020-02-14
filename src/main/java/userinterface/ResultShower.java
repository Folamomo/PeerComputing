package userinterface;

import computinglib.TaskManager;
import computinglib.TaskRepository;
import application.Task_Primes;
import peerlibremastered.Message;

public class ResultShower extends Action {
    public ResultShower(TaskManager manager) {
        this.manager = manager;
    }

    private TaskManager manager;

    @Override
    void doAction() {
        System.out.print("Results: \n");
        for(Object task : manager.getRepository().tasks.values()){
            if (((Task_Primes)task).isDone()){
                System.out.print(((Task_Primes) task).result + " \n");
            }
        }



    }
}
