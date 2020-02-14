package computinglib;

import peerlib.Peer;

import java.util.Collection;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

import static computinglib.Status.IN_PROGRESS;

public class TaskRepository<ResultType> {
    SortedMap<Integer, Task<ResultType>> tasks = new ConcurrentSkipListMap<>();

    public void addTasks(Collection<Task<ResultType>> toAdd) {
        toAdd.forEach(task -> tasks.put(task.id, task));
    }

    public void addTask(Task<ResultType> toAdd) {
        tasks.put(toAdd.id, toAdd);
    }

    public Optional<Task<ResultType>> getTask(int id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public Optional<Task<ResultType>> getFirstFreeTask() {
        return tasks.values().parallelStream().filter(Task::isFree).filter(Task::dependenciesReady).findFirst();
    }

    public void saveDoneTaskFromPeer(Task<ResultType> task) {
        tasks.put(task.id, task);
    }

    public void markAsInProgress(int id, Peer peer) {
        Task<ResultType> task = tasks.get(id);
        task.setHandledBy(peer);
        task.setStatus(IN_PROGRESS);
    }
}
