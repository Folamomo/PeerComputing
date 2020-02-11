package computinglib;

//import computinglib.messages.TaskCompletedMessage;
//import computinglib.messages.TaskStartedMessage;
import peerlib.Peer;
import peerlib.PeerFacade;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static computinglib.Status.IN_PROGRESS;

public class TaskManager<ResultType> implements Runnable {
    private Peer me;

    public TaskRepository<ResultType> getRepository() {
        return repository;
    }

    private TaskRepository<ResultType> repository;
    private PeerFacade peers;
    private ThreadPoolExecutor executor;
    private List<Task<ResultType>> running;

    private int coreThreads = 4;

    public TaskManager(TaskRepository<ResultType> repository, PeerFacade peers) {
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

    private void runTask(Task<ResultType> task) {
        task.setHandledBy(me);
        running.add(task);
        executor.execute(task);
//        peers.SendToAll(new TaskStartedMessage(task));
    }

    private void saveCompletedTasks() {
        for (Task<ResultType> task : running) {
            if (task.isDone()) {
//                peers.SendToAll(new TaskCompletedMessage(task));
                running.remove(task);
            }
        }
    }

    private void handleMessagesFromPeers() {
       // peers.getMessages().forEach(message -> message.handle(this));
    }

    public void handlePeerTaskStartedMessage(int taskId, Timestamp startedAt, Peer peer) {
        Task<ResultType> task = repository.getTask(taskId).orElseThrow(RuntimeException::new);
        if (task.getStatus() == IN_PROGRESS && task.getHandledBy() == me) {

        }
    }

    public void addTask(Task t){
        this.repository.addTask(t);
    }
    public void handlePeerTaskCompletedMessage(Task<ResultType> task) {
        repository.saveDoneTaskFromPeer(task);
    }

    public void handleNewTaskMessage(Task task) {
        repository.addTask(task);
    }
}
