package peerlibremastered;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerMessageReader implements Runnable{

    private int portNum;

    public ServerMessageReader(int portNum, Socket clientSocket) {
        this.portNum = portNum;
        this.clientSocket = clientSocket;
    }

    private Socket clientSocket;

    @Override
    public void run() {
        while (true) {
            String address = clientSocket.getInetAddress().getHostAddress();
            System.out.print("READER Handling adress: " +  address  + "\n");
            // Creating inout and output streams. Must creat out put stream first.
            //ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                System.out.print("EEEE");
                e.printStackTrace();
            }
            System.out.print("LALLALALALAAL");
            // Reading in Integer Object from input stream.


            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("READER waiting for new message...");
                Message message = null;
                try {
                    message = (Message) in.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("READER Received message:" + message.type);
                try {
                    handleMessage(message, portNum, clientSocket);
                } catch (IOException | ClassNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("READER Message " + message.type + " handled successfully.\n");
                //TODO Tutaj dochodzi kod, ale nwm czemu nie wchodzi spowrotem w while'a... Do sprawdzenia
                System.out.flush();
            }
        }
    }

    public void handleMessage(Message message, Integer portNum, Socket socket) throws IOException, ClassNotFoundException, InterruptedException {
        MessageHandler messageHandler = new MessageHandler(message, portNum, socket);
        messageHandler.handle();
    }
}
