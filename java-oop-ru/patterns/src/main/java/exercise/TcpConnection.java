package exercise;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection implements Connection{
   private Connection status;
   private String ip;
   private int port;

    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.status = new Disconnected(this);
    }

    public void setStatus(Connection status) {
        this.status = status;
    }

    @Override
    public void connect() {
        this.status.connect();
    }

    @Override
    public void disconnect() {
        this.status.disconnect();
    }

    @Override
    public String getCurrentState() {
        return this.status.getCurrentState();
    }

    @Override
    public void write(String data) {
        this.status.write(data);
    }
}
// END
