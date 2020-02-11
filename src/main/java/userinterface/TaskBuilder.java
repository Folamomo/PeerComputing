package userinterface;

import computinglib.Task;
import computinglib.TaskManager;
import computinglib.TaskRepository;
import computinglib.Task_Primes;
import peerlib.PeerFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class TaskBuilder extends Action{
    private  PeerFacade peers;
    private TaskManager manager;

    TaskBuilder(PeerFacade peers, TaskManager manager){
        this.peers = peers;
        this.manager = manager;
    }


    @Override
    void doAction() {
        System.out.print("Prime numer calculation\n");
        Long range = this.getRangeValue();
        int c = 0;
        Collection<Task_Primes> tasks = new ArrayList<>();

        for(long i= 0; i < Math.floor(range/1000); i++){
            List<Task_Primes> dependencies = new ArrayList<Task_Primes>();

            for(Task_Primes t : tasks){
                if (t.getB() <= Math.sqrt(i*100 + 1000)){
                    dependencies.add(t);
                }
            }


            Task_Primes task = new Task_Primes(c, i*1000, i*1000 + 1000, dependencies);

            manager.addTask(task);

            tasks.add(task);
            c++;
        }
        System.out.print("Task added!\n");
    }

    private Long getRangeValue(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter range for primes computation: \n");
        return input.nextLong();
    }
}
