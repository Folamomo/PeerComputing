package peerlibremastered;

import java.io.IOException;
import java.net.CookiePolicy;
import java.net.Socket;
import java.util.List;

public class ConnectionMenager{
    private Integer serverPort;


    public List<Connection> connections;

    public ConnectionMenager(Integer serverport, List<Connection> connections) {
        this.serverPort = serverport;
        this.connections = connections;
    }

    public void sendToAll(Message message) throws IOException, InterruptedException {
        System.out.print("Sending to all");
        System.out.print(connections.size());
        for(Connection connection : connections){
            System.out.print("Sending to " + connection.remoteHost);
            if (message.from == null){
                message.setFrom(this.serverPort);
            }

            Socket s = new Socket(connection.remoteServer, connection.remoteHost);

            PeerClient p = new PeerClient(connection.remoteHost, s );
            p.sendMessage(message);

        }
    }

}
