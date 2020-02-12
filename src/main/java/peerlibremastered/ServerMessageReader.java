package peerlibremastered;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerMessageReader implements Runnable{
    private ConnectionMenager connectionMenager;
    private int portNum;

    public ServerMessageReader(int portNum, Socket clientSocket, ConnectionMenager connectionMenager) {
        this.portNum = portNum;
        this.serverSocket = clientSocket;
        this.connectionMenager = connectionMenager;
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
                } catch (SocketException e){
                    System.out.print("Closing reader. Peer down. Closing: " + address + " \n");
                    return;
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("READER on port " + portNum+ " Received message:" + message.type + " from " + message.from + ", "+ message.adress + "\n");
                Socket socket = null;
                if(message.from == null){
                    System.out.print("NNNNNNNNNNIIIIIIIIIIIIEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                }
                try {
                    socket = new Socket(message.adress, message.from);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    handleMessage(message, message.from, message.adress, portNum, connectionMenager.serverAddress, socket, connectionMenager);
                } catch (IOException | ClassNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.print("READER Message " + message.type + " handled successfully.\n");
                System.out.flush();
                return;
            }
        }
    }

    public void handleMessage(Message message, Integer portNum, String address, Integer fromPort, String fromAddress, Socket socket, ConnectionMenager connectionMenager) throws IOException, ClassNotFoundException, InterruptedException {
        MessageHandler messageHandler = new MessageHandler(message, portNum, address, fromPort, fromAddress, socket, connectionMenager);
        Thread handling = new Thread(messageHandler);
        handling.start();
    }
}
