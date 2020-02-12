package peerlibremastered;

import java.io.Serializable;
import java.util.Objects;

public class Connection implements Serializable {
    public String remoteServer;
    public Integer remoteHost;

    public Connection(String remoteServer, Integer remoteHost) {
        this.remoteServer = remoteServer;
        this.remoteHost = remoteHost;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return Objects.equals(remoteServer, that.remoteServer) &&
                Objects.equals(remoteHost, that.remoteHost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteServer, remoteHost);
    }
}
