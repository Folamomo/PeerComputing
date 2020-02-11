package peerlibremastered;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerMessageReader implements Runnable{

    private int portNum;

    public ServerMessageReader(int portNum, Socket clientSocket) {
        this.portNum = portNum;
        this.serverSocket = clientSocket;
    }

    private Socket serverSocket;

    @Override
    public void run() {
        while (true) {
            String address = serverSocket.getInetAddress().getHostAddress();
            System.out.print("READER Handling adress: " +  address  + "\n");
            // Creating inout and output streams. Must creat out put stream first.
            //ObjectOutputStream out = new ObjectOutputStream(serverSocket.getOutputStream());
            ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(serverSocket.getInputStream());
            } catch (IOException e) {
                System.out.print("EEEError");
                e.printStackTrace();
            }
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
                Socket socket = null;
                if(message.from == null){
                    System.out.print("NNNNNNNNNNIIIIIIIIIIIIEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                }
                try {
                    socket = new Socket("127.0.0.1", message.from);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    handleMessage(message, message.from, socket);
                } catch (IOException | ClassNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("READER Message " + message.type + " handled successfully.\n");
                System.out.flush();

            }
        }
    }

    public void handleMessage(Message message, Integer portNum, Socket socket) throws IOException, ClassNotFoundException, InterruptedException {
        MessageHandler messageHandler = new MessageHandler(message, portNum, socket);
        Thread handling = new Thread(messageHandler);
        handling.start();
    }
}
