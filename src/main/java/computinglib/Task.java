package computinglib;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.RunnableFuture;

public abstract class Task<ResultType> implements RunnableFuture<ResultType>, Serializable, Comparable<Task<ResultType>> {
    //   @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected final int id;
    private boolean isDone;
    private ResultType result;
    private Collection<Task<ResultType>> dependencies;

    protected Task(int id) {
        this.id = id;
    }
    //To jest wzorzec template method

    @Override
    public void run() {
        if (dependencies.stream().allMatch(Task::isDone)) {
            result = calculate();
            isDone = true;
        } else throw new RuntimeException();
    }

    abstract ResultType calculate();

    public boolean isDone() {
        return isDone;
    }


    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int compareTo(Task<ResultType> o) {
        return Integer.compare(id, o.id);
    }
}
