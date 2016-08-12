package chat;

import protocol.ErrDGram;
import protocol.GoodbyeDGram;
import protocol.HelloDGram;
import protocol.OkayDGram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Connection {

    final Node              _RefParentNode;

    final User              _User;
    final Socket            _Socket;

    final BufferedReader    _ConnReader;
    final PrintWriter       _ConnWriter;


    Connection(Node refParentNode, Socket socket) throws IOException {
        _Socket = socket;
        _ConnReader = new BufferedReader(new InputStreamReader(socket().getInputStream()));
        _ConnWriter = new PrintWriter(socket.getOutputStream());
        _RefParentNode = refParentNode;

        // Follows the protocol to establish the connection!
        String initVerb = _ConnReader.readLine();

        if (initVerb.equals(HelloDGram.VERB)) {
            HelloDGram hello = HelloDGram.importFromString(connReader());
            if (refParentNode.verifyPassword(hello.password()))
                _User = hello.greeter();
            else
                _User = null;

            confirmSuccess(HelloDGram.VERB);
        }
        else {
            notifyFailure("Invalid verb! Required a Hello DGram to initiate connection.");
            throw new IOException("Invalid verb! Required a Hello DGram!");
        }
    }


    void disconnect() {
        GoodbyeDGram bye = new GoodbyeDGram(user().address());

        // Sending the goodbye datagram:
        connWriter().write(bye.export());

        try {
            _Socket.close();
        }
        catch (IOException e) {
            System.err.println("Failed to disconnect from a remote client!");
            e.printStackTrace();
        }
    }


    void confirmSuccess(String verb) {
        OkayDGram okay = new OkayDGram(user().address(), verb);
        connWriter().write(okay.export());
    }



    void notifyFailure(String reason) {
        ErrDGram error = new ErrDGram(user().address(), reason);
        connWriter().write(error.export());
    }


    Socket          socket()        { return _Socket; }
    User            user()          { return _User; }
    BufferedReader  connReader()    { return _ConnReader; }
    PrintWriter     connWriter()    { return _ConnWriter; }
    Node            parentNode()    { return _RefParentNode; }

    boolean valid() { return user() != null; }
}
