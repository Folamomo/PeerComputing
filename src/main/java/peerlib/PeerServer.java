package peerlib;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static peerlib.MessageType.SHAKE;

public class PeerServer implements Runnable {

    private ServerSocket serverSocket;

    public PeerServer() throws IOException {
        //this.serverSocket = new ServerSocket(12345);
    }

    @Override
    public void run(){

        // Port number to bind server to.
        int portNum = 11113;

        // Socket for server to listen at.
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(portNum);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server is now running at port: " + portNum);

        // Simply making Server run continously.
        while (true) {
            try {
                // Accept a client connection once Server recieves one.
                Socket clientSocket = listener.accept();

                // Creating inout and output streams. Must creat out put stream first.
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

                // Reading in Integer Object from input stream.
                Message message = (Message) in.readObject();


                handleMessage(message);

                //Sending response back to client
                String response = "Message Object Received.";
                out.writeObject(response);

                // Outputting recieved Integer Object.
                System.out.println("Received message:" + message.type);
                out.close();
                in.close();
                clientSocket.close();
                break;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                // Closing Server Socket now.
                try {
                    listener.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }



        }
    }
    void handleMessage(Message message){
        switch (message.type) {
            case ERROR:
                throw new RuntimeException();
            case HAND:
                break;
            case SHAKE:
                break;
            case PING:
                break;
            case PONG:
                break;
            case GET_PEERS:
                break;
            case PEERS:
                break;
            case DATA:
                break;
            default:
        }
    }
}
