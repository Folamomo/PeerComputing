package application;

import computinglib.TaskManager;
import computinglib.TaskRepository;
import org.junit.Test;
import peerlibremastered.PeerFacade;
import userinterface.TaskBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;

public class NoPeersTest {

    @Test
    public void noPeers() throws InterruptedException {
        PeerFacade peerFacade = mock(PeerFacade.class);
        when(peerFacade.getAllMessages()).thenReturn(List.of());
        doNothing().when(peerFacade).sendToAll(any());

        TaskManager<List<Long>> manager = new TaskManager<>(new TaskRepository<List<Long>>(), peerFacade);

        TaskBuilder builder = new TaskBuilder(manager);

        Thread taskThread = new Thread(manager);
        taskThread.start();

        builder.doAction();

        TimeUnit.SECONDS.sleep(10L);
    }


}
