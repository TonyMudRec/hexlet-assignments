package exercise.connections;

import exercise.TcpConnection;

// BEGIN
class Connected implements Connection {
    private TcpConnection tcpConnection;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection already established");
    }

    @Override
    public void disconnect() {
        this.tcpConnection.setStatus(new Disconnected(this.tcpConnection));
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void write(String data) {
        System.out.println("data was written");
    }
}
// END
