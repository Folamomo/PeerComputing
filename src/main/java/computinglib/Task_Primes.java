package computinglib;

import computinglib.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class Task_Primes extends Task<List<Long>> {

    private final Long a;

    public Long getA() {
        return a;
    }

    public Long getB() {
        return b;
    }

    private final Long b;

    public Task_Primes(int id, Long a, Long b, Collection<Task_Primes> dependencies) {
        super(id);
        this.a = a;
        this.b = b;
        this.dependencies.addAll(dependencies);
    }

    @Override
    public List<Long> calculate() {
        List<Long> other_results;
        if (!this.dependencies.isEmpty()) {
            // Wypełnianie other results poprzednio znalezionymi liczbami pierwszymi
            other_results = dependencies
                    .stream()
                    .flatMap(task -> task.getResult().stream())
                    .collect(Collectors.toList());
            List<Long> toReturn = new LinkedList<>();


//            Collections.sort(other_results);
//            // na wypadek gdyby nie były pełne dane
//            for (int i = other_results.get(other_results.size() - 1) + 1; i < Math.sqrt(b); i++) {
//                other_results.add(i);
//            }

            System.out.println(other_results);
        } else {
            other_results = new ArrayList<>();
            for (long i = 2; i < Math.sqrt(b); i++) {
                other_results.add(i);
            }
        }

        // Wypełnianie tablicy wyników liczbami z zakresu
        for (Long i = a; i <= b; i++) {
            result.add(i);
        }

        System.out.println(result);

        //Sito
        for (Long i : other_results) {
            for (long j = a - a % i; j <= b; j = j + i) {
                result.remove(j);
            }
        }
        System.out.println(result);
        return result;
    }

    public void saveToFile(String filepath) throws IOException {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(filepath, true);
            StringBuilder result_string = new StringBuilder();
            for (Long a : this.getResult()) {
                result_string.append(a.toString()).append(" ");
            }
            fileWriter.append(result_string.toString());
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }


    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public List<Long> get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public List<Long> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
