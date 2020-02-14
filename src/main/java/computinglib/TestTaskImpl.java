package computinglib;

import computinglib.Task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestTaskImpl extends Task<Integer> {
    protected TestTaskImpl(int id) {
        super(id);
    }

    public Integer calculate(){
        System.out.println("Task " + id + " is calculated.");
        return id;
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
    public Integer get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Integer get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
