package computinglib;

import org.junit.Test;
import org.mockito.*;
import peerlibremastered.Message;
import peerlibremastered.PeerFacade;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class TaskManagerTest {



    @Test public void addTaskTest() throws InterruptedException {
        PeerFacade peerFacade = mock(PeerFacade.class);

        when(peerFacade.getAllMessages()).thenReturn(List.of());
        doNothing().when(peerFacade).sendToAll(any());
        TaskManager<Integer> taskManager = new TaskManager<Integer>(new TaskRepository<Integer>(), peerFacade);

        Thread taskThread = new Thread(taskManager);
        taskThread.start();

        taskManager.addTask(new TestTaskImpl(1));

        TimeUnit.SECONDS.sleep(2L);
    }

    @Test public void addTasksTest() throws InterruptedException {
        PeerFacade peerFacade = mock(PeerFacade.class);

        when(peerFacade.getAllMessages()).thenReturn(List.of());
        doNothing().when(peerFacade).sendToAll(any());
        TaskManager<Integer> taskManager = new TaskManager<Integer>(new TaskRepository<Integer>(), peerFacade);

        Thread taskThread = new Thread(taskManager);
        taskThread.start();

        TestTaskImpl task1 = new TestTaskImpl(1);
        TestTaskImpl task2 = new TestTaskImpl(2);
        TestTaskImpl task3 = new TestTaskImpl(3);

        task2.dependencies.add(task1);
        task3.dependencies.add(task2);

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);


        TimeUnit.SECONDS.sleep(2L);

        System.out.println(task3.result);
    }

}