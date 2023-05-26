package exercise.connections;

public interface Connection {
    // BEGIN
    public void connect();
    public void disconnect();
    public String getCurrentState();
    public void write(String data);
    // END
}
