package peerlib;

import peerlibremastered.Message;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Peer implements Closeable {
    public void setId(Long id) {
        this.id = id;
    }

    Long id;
    private Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    Peer(Socket socket) throws IOException {
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    void send(Message message) throws IOException {
        out.writeObject(message);
    }

    List<Message> receive() throws IOException, ClassNotFoundException {
        List<Message> result = new ArrayList<>();
        while (in.available() > 0) {
            result.add((Message) in.readObject());
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
