package computinglib;

import java.util.Collection;
import java.util.Optional;
import java.util.SortedSet;

public class TaskRepository<ResultType> {
    SortedSet<Task<ResultType>> tasks;

    public void addTasks(Collection<Task<ResultType>> toAdd) {
        tasks.addAll(toAdd);
    }

    public Optional<Task<ResultType>> getFirstFreeTask() {
        return tasks.stream().filter(Task::isFree).filter(Task::dependenciesReady).findFirst();
    }

    public void saveDoneTaskFromPeer(Task<ResultType> task) {
        tasks.remove(task);
        tasks.add(task);
    }
}
