package userinterface;

import computinglib.TaskManager;
import computinglib.TaskRepository;
import computinglib.Task_Primes;

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

        while(rep.getTask(id).isPresent() && ((Task_Primes)rep.getTask(id).get()).isDone()){
            System.out.print(((Task_Primes)rep.getTask(id).get()).result + ", ");
            id ++;
        }



    }
}
