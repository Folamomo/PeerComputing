package computinglib;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.RunnableFuture;

import static computinglib.Status.*;

public abstract class Task<ResultType> implements RunnableFuture<ResultType>, Serializable, Comparable<Task<ResultType>> {
    protected final int id;
    private Status status;
    public ResultType result;
    public Collection<Task<ResultType>> dependencies;

    protected Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        this.status = IN_PROGRESS;
        result = this.calculate();
        this.status = DONE;
    }

    public boolean dependenciesReady() {
        return dependencies.stream().allMatch(Task::isDone);
    }

    public ResultType getResult() {
        return result;
    }

    abstract ResultType calculate();

    public boolean isDone() {
        return status == DONE;
    }

    public boolean isFree() {
        return status == FREE;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int compareTo(Task<ResultType> o) {
        return Integer.compare(id, o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task<?> task = (Task<?>) o;
        return id == task.id;
    }
}
