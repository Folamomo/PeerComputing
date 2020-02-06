package v1;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.RunnableFuture;

public abstract class Task implements RunnableFuture, Serializable, Comparable<Task> {
    //   @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected final int id;
    boolean isDone;
    private LinkedList<Integer> result;
    protected LinkedList<Task> dependencies;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
            this.result = calculate();
            isDone = true;
    }

    abstract protected LinkedList<Integer> calculate();

    public boolean isDone() {
        return isDone;
    }

    public LinkedList<Integer> getResult(){
        return result;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(id, o.id);
    }

}
