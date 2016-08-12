package chat;

import common.Address;

import java.util.List;


public class Node {

    User                _SelfUser;
    List<Connection>    _ConnectedUsers;

    String              _Password;


    Node(User self) {
        _SelfUser = self;

    }


    void connectTo(Address remoteClient) {

    }


    void message(String message, Address remoteRecipient) {

    }


    void disconnect(Address remoteClient) {

    }


    boolean verifyPassword(String password) {
        return password.equals(_Password);
    }


    User selfUser() { return _SelfUser; }
}
