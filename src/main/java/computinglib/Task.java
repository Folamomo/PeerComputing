package computinglib;

import peerlib.Peer;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.RunnableFuture;

import static computinglib.Status.*;

public abstract class Task<ResultType> implements RunnableFuture<ResultType>, Serializable, Comparable<Task<ResultType>> {
    protected final int id;
    private Instant startedAt;
    private Status status;
    public Peer handledBy;
    public ResultType result;
    public Collection<Task<ResultType>> dependencies;

    protected Task(int id) {
        this.id = id;
        status = FREE;
        dependencies = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void run() {
        if (this.status == FREE) {
            this.status = IN_PROGRESS;
            this.startedAt = Instant.now();
            result = this.calculate();
            this.status = DONE;
        }
    }

    public boolean dependenciesReady() {
        return dependencies.parallelStream().allMatch(Task::isDone);
    }

    public ResultType getResult() {
        return result;
    }

    public abstract ResultType calculate();

    public boolean isDone() {
        return status == DONE;
    }

    public boolean isFree() {
        return status == FREE;
    }

    public int getId() {
        return id;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Peer getHandledBy() {
        return handledBy;
    }

    public void setHandledBy(Peer handledBy) {
        this.handledBy = handledBy;
    }

    public void setResult(ResultType result) {
        this.result = result;
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
