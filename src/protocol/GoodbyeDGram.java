package protocol;

import common.Address;

import java.io.BufferedReader;
import java.io.IOException;


public class GoodbyeDGram extends DGram {

    public static final String VERB = "BYE";

    final Address _DisconnectingGuestAddress;

    public GoodbyeDGram(Address disconnectingGuestAddress) {
        _DisconnectingGuestAddress = disconnectingGuestAddress;
    }


    @Override
    public String export() {
        return  VERB                                    + '\n'  +       // Line 1: Verb
                _DisconnectingGuestAddress.hostName()   + '\n'  +       // Line 2: Disconnecting guest's host name
                _DisconnectingGuestAddress.port()       + '\n';         // Line 3: Disconnecting guest's port
    }


    public static GoodbyeDGram importFromString(BufferedReader reader) throws IOException {
        String hostName = reader.readLine();                // Line 2: Disconnecting guest's host name
        int port = Integer.parseInt(reader.readLine());     // Line 3: Disconnecting guest's port
        return new GoodbyeDGram(new Address(hostName, port));
    }

}
