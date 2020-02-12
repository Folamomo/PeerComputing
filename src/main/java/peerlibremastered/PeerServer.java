package peerlibremastered;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PeerServer implements Runnable {
    public Integer portNum;
    private ServerSocket serverSocket;
    private ConnectionMenager connectionMenager;

    public PeerServer(int portNum, ConnectionMenager connectionMenager) throws IOException {
        this.portNum = portNum;
        this.connectionMenager = connectionMenager;
    }

    public void start(){
        // Socket for server to listen at.
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(this.portNum);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.serverSocket = listener;
    }

    @Override
    public void run() {
            // Port number to bind server to.
            this.start();

            System.out.println("SERWER is now running at port: " + this.portNum);

            while (true) {
                System.out.print("SERWER Waiting for connection...\n");

                // Simply making Server run continously.
                try {
                    // Accept a client connection once Server recieves one.
                    Socket clientSocket = this.serverSocket.accept();
                    System.out.print("SERWER Accepted connection." + "\n");
                    ServerMessageReader serverMessageReader = new ServerMessageReader(this.portNum, clientSocket, connectionMenager);
                    new Thread(serverMessageReader).start();

//                    String address = clientSocket.getInetAddress().getHostAddress();
//                    System.out.print("SERWER Adress: " +  address + " connected." + "\n");
//                    // Creating inout and output streams. Must creat out put stream first.
//                    //ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
//                    ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
//
//                    // Reading in Integer Object from input stream.
//
//
//                    while (true) {
//                        Message message = (Message) in.readObject();
//                        System.out.println("SERWER Received message:" + message.type);
//
//                        //String response = "Message Object Received.";
//
//                        handleMessage(message, portNum, clientSocket);
//                    }



                    //Sending response back to client
                    //out.writeObject(response);

                    //out.close();
                    //in.close();
                } catch (IOException e) {
                    System.out.print("COCOCO");
                    e.printStackTrace();
                } finally {
                    System.out.print("Finally closed server\n");
                    // Closing Server Socket now.
//                    try {
//                        this.serverSocket.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                }
            }
        }

//    public void handleMessage(Message message, Integer portNum, Socket socket) throws IOException, ClassNotFoundException, InterruptedException {
//        MessageHandler messageHandler = new MessageHandler(message, portNum, socket);
//        messageHandler.handle();
//    }
}
