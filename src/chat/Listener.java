package chat;

import common.Address;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Listener implements Runnable {

    final Node          _RefParentNode;
    final ServerSocket  _ServerSocket;
    boolean             _IsRunning;


    Listener(Node refParentNode, Address listenAddress) throws IOException {
        _RefParentNode = refParentNode;
        _ServerSocket = new ServerSocket(listenAddress.port(),
                                         10,  // Back-log
                                         InetAddress.getByName(listenAddress.hostName()));
        _IsRunning = false;
    }


    public void run() {
        _IsRunning = true;

        while (true) {
            try {
                Socket connectionSocket = _ServerSocket.accept();

                // Connection accepted!
                Connection newConnection = new Connection(parentNode(), connectionSocket);

            } catch (IOException e) {
                System.err.println("Something went wrong when handling incoming connections!");
                e.printStackTrace();
                break;
            }
        }

        try {
            _ServerSocket.close();
        }
        catch (IOException e) {
            System.err.println("Something went wrong while closing the listener socket!");
        }
    }


    Node parentNode() { return _RefParentNode; }

}
