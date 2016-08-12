package common;


public class Address {

    private final String _HostName;
    private final int    _Port;


    public Address(String hostName, int port) {
        _HostName = hostName;
        _Port = port;
    }


    public String  hostName() { return _HostName; }
    public int     port() { return _Port; }
}
