package computinglib;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class Task_Primes extends Task<List<Integer>> {

    private final int a;
    private final int b;

    public Task_Primes(int id, int a, int b, Collection<Task<List<Integer>>> dependencies) {
        super(id);
        this.a = a;
        this.b = b;
        this.dependencies = dependencies;
    }

    @Override
    public List<Integer> calculate() {
        if (this.dependencies != null) {
            // Wypełnianie other results poprzednio znalezionymi liczbami pierwszymi
            List<Integer> other_results = dependencies
                    .stream()
                    .flatMap(task -> task.getResult().stream())
                    .collect(Collectors.toList());
            LinkedList<Integer> result = new LinkedList<>();


            Collections.sort(other_results);
            // na wypadek gdyby nie były pełne dane
            for (int i = other_results.get(other_results.size() - 1) + 1; i < Math.sqrt(b); i++) {
                other_results.add(i);
            }

            System.out.println(other_results);

            // Wypełnianie tablicy wyników liczbami z zakresu
            for (int i = a; i <= b; i++) {
                result.add((Integer) i);
            }
            Collections.sort(result);
            System.out.println(result);

            //Sito
            for (Integer i : other_results) {
                for (int j = i; j <= b; j = j + i) {
                    if (result.contains(j)) {
                        result.remove((Integer) j);
                    }
                }
            }
            System.out.println(result);
            return result;
        } else {
            LinkedList<Integer> result = new LinkedList<>();
            LinkedList<Integer> numbers = new LinkedList<>();

            // Wypełnianie tablicy wyników liczbami z zakresu
            for (int i = a; i <= b; i++) {
                result.add((Integer) i);
            }
            Collections.sort(result);


            // Wypełnianie tablicy liczbami z zakresu 2 do sqrt(b)
            for (int i = 2; i < Math.sqrt(b); i++) {
                numbers.add((Integer) i);
            }
            Collections.sort(numbers);

            // Sito
            for (int i : numbers) {
                for (int j = i + i; j <= b; j = j + i) {
                    if (result.contains((Integer) j)) {
                        result.remove((Integer) j);
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
            for (Integer a : this.getResult()) {
                result_string.append(a.toString() + " ");
            }
            fileWriter.append(result_string.toString());
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }


    }

    ;

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public List<Integer> get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public List<Integer> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
