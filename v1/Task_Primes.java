package v1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.lang.Math;
import java.lang.Integer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Task_Primes extends Task {

    private final int a;
    private final int b;

    public Task_Primes(int id, int a, int b, LinkedList<Task> dependencies){
        super(id);
        this.a = a;
        this.b = b;
        this.dependencies = dependencies;
    }

    @Override
    public LinkedList<java.lang.Integer> calculate(){
        if (this.dependencies != null){
            LinkedList<java.lang.Integer> other_results = new LinkedList<>();
            LinkedList<java.lang.Integer> result = new LinkedList<>();

            // Wypełnianie other results poprzednio znalezionymi liczbami pierwszymi
            for (Task task: this.dependencies){
                for (java.lang.Integer prime: task.getResult()) {
                    if (prime < Math.sqrt(b)){
                        other_results.add(prime);
                    }
                }
            }

            Collections.sort(other_results);
            // na wypadek gdyby nie były pełne dane
            for (int i = other_results.get(other_results.size()-1) + 1; i < Math.sqrt(b); i ++){
                other_results.add(i);
            }

            System.out.println(other_results);

            // Wypełnianie tablicy wyników liczbami z zakresu
            for (int i = a; i <= b; i++ ){
                result.add((java.lang.Integer)i);
            }
            Collections.sort(result);
            System.out.println(result);

            //Sito
            for(java.lang.Integer i: other_results){
                for(int j = i; j <= b; j=j+i){
                    if(result.contains((java.lang.Integer)j)){
                        result.remove((java.lang.Integer)j);
                    }
                }
            }
            this.isDone = true;
            System.out.println(result);
            return result;
        }

        else{
            LinkedList<java.lang.Integer> result = new LinkedList<>();
            LinkedList<java.lang.Integer> numbers = new LinkedList<>();

            // Wypełnianie tablicy wyników liczbami z zakresu
            for (int i = a; i <= b; i++ ){
                result.add((java.lang.Integer)i);
            }
            Collections.sort(result);


            // Wypełnianie tablicy liczbami z zakresu 2 do sqrt(b)
            for (int i = 2; i < Math.sqrt(b); i++){
                numbers.add((java.lang.Integer)i);
            }
            Collections.sort(numbers);

            // Sito
            for(int i: numbers){
                for(int j = i+i; j <= b; j=j+i){
                    if(result.contains((java.lang.Integer)j)){
                        result.remove((java.lang.Integer)j);
                    }
                }
            }
            return result;
            }

        }

    public void saveToFile(String filepath) throws IOException {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(filepath, true);
            StringBuilder result_string = new StringBuilder();
            for (Integer a: this.getResult()){
                result_string.append(a.toString() + " ");
            }
            fileWriter.append(result_string.toString());
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }


    };

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
