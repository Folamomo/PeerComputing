package computinglib;

import computinglib.messages.TaskCompletedMessage;
import computinglib.messages.TaskStartedMessage;
import peerlib.Peer;
import peerlib.PeerFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskManager implements Runnable {
    private TaskRepository<?> repository;
    private PeerFacade peers;
    private ThreadPoolExecutor executor;
    private List<Task<?>> running;

    private int coreThreads = 4;

    public TaskManager(TaskRepository<?> repository, PeerFacade peers) {
        this.repository = repository;
        this.peers = peers;
        executor = new ThreadPoolExecutor(coreThreads, coreThreads, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(8));
        running = new ArrayList<>();
    }

    @Override
    public void run() {
        runTasksIfFreeThreads();
        saveCompletedTasks();
        handleMessagesFromPeers();
    }

    private void runTasksIfFreeThreads() {
        if (executor.getActiveCount() < coreThreads) {
            repository.getFirstFreeTask().ifPresent(this::runTask);
        }
    }

    private void runTask(Task<?> task) {
        running.add(task);
        executor.execute(task);
        peers.SendToAll(new TaskStartedMessage(task));
    }

    private void saveCompletedTasks() {
        for (Task<?> task : running) {
            if (task.isDone()) {
                peers.SendToAll(new TaskCompletedMessage(task));
                running.remove(task);
            }
        }
    }

    private void handleMessagesFromPeers() {
        peers.getMessages().forEach(message -> message.handle(this));
    }

    public void handlePeerTaskStartedMessage(Long taskId, Peer peerId) {

    }
}
