package computinglib;

//import computinglib.messages.TaskCompletedMessage;
//import computinglib.messages.TaskStartedMessage;
import peerlibremastered.Connection;
import peerlibremastered.Message;
import peerlibremastered.PeerFacade;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

import static computinglib.Status.IN_PROGRESS;
import static peerlib.MessageType.DATA;
import static peerlib.MessageType.REQUEST_DATA;

public class TaskManager<ResultType> implements Runnable {
    public TaskRepository<ResultType> getRepository() {
        return repository;
    }

    private TaskRepository<ResultType> repository;
    private PeerFacade peers;
    private ThreadPoolExecutor executor;
    private Collection<Task<ResultType>> running;

    private int coreThreads = 8;

    public TaskManager(TaskRepository<ResultType> repository, PeerFacade peers) {
        this.repository = repository;
        this.peers = peers;
        executor = new ThreadPoolExecutor(0, coreThreads, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(8));
        running = new ConcurrentLinkedQueue<>();
//        executor.setRejectedExecutionHandler();
    }

    @Override
    public void run() {
        while (true) {
            runTasksIfFreeThreads();
            try {
                saveCompletedTasks();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handleMessagesFromPeers();
        }
    }

    private void runTasksIfFreeThreads() {
        if (executor.getQueue().remainingCapacity() > 1) {
            repository.getFirstFreeTask().ifPresent(this::runTask);
        }
    }

    private void runTask(Task<ResultType> task){
//        task.setHandledBy(me);
        running.add(task);
        executor.execute(task);
        peers.sendToAll(new Message(DATA, task));
    }

    private void saveCompletedTasks() throws InterruptedException {
        for (Task<ResultType> task : running) {
            if (task.isDone()) {
                peers.sendToAll(new Message(DATA, task));
                running.remove(task);
            }
        }
    }

    private void handleMessagesFromPeers() {
        Collection<Message> messages = peers.getAllMessages();
        for (Message message : messages){
            if (message.type == DATA) {
                Task<ResultType> task = (Task<ResultType>) message.payload;
                if (task.isDone()) repository.saveDoneTaskFromPeer(task);
                else if (task.isFree()) repository.addTask(task);
                else if (task.getStatus() == IN_PROGRESS) {

                    Optional<Task<ResultType>> myVersion = repository.getTask(task.id);
                    if (myVersion.isEmpty()) repository.addTask(task);
                    if (myVersion.get().getStatus() == IN_PROGRESS && myVersion.get().getStartedAt().isAfter(task.getStartedAt())) {
                        myVersion.get().cancel(true);
                        running.remove(myVersion.get());
                    }
                    repository.addTask(task);
                }
            }
            else if (message.type == REQUEST_DATA) {

                for (Task task : repository.tasks.values()){
                    peers.sendToSpecific(
                            new Message(
                                    peers.connectionMenager.serverPort,
                                    peers.connectionMenager.serverAddress,
                                    DATA,
                                    task),
                            new Connection(message.adress, message.from));
                }
            }
        }
    }

    public void addTask(Task<ResultType> t){
        peers.sendToAll(new Message(DATA, t));
        this.repository.addTask(t);
    }
}
