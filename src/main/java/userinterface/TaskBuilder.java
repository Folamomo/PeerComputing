package userinterface;

import computinglib.TaskManager;
import application.Task_Primes;
import peerlibremastered.PeerFacade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class TaskBuilder extends Action{
    private TaskManager<List<Long>> manager;

    public TaskBuilder( TaskManager<List<Long>> manager){
        this.manager = manager;
    }


    @Override
    public void doAction() {
        System.out.print("Prime numer calculation\n");
//        Long range = this.getRangeValue();
        Long range = 1000000L;
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
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter range for primes computation: \n");
        try {
            return Long.valueOf(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}
