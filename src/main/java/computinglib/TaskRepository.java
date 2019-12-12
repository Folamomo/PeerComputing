package computinglib;

import peerlib.PeersManager;

import java.util.Collection;
import java.util.SortedSet;

public class TaskRepository<T> {
    SortedSet<Task<T>> tasks;
    PeersManager peersManager;

    public void addTasks(Collection<Task<T>> toAdd) {
        tasks.addAll(toAdd);
        peersManager.getPeers().forEach(socket -> {/*send new tasks*/});
    }
}
