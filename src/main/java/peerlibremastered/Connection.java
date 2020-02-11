package peerlibremastered;

import java.io.Serializable;

public class Connection implements Serializable {
    public String remoteServer;
    public Integer remoteHost;

    public Connection(String remoteServer, Integer remoteHost) {
        this.remoteServer = remoteServer;
        this.remoteHost = remoteHost;
    }



}
