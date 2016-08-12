package protocol;

import common.Address;

import java.io.BufferedReader;
import java.io.IOException;


public class OkayDGram extends DGram {

    public static final String VERB = "OKAY";

    final Address   _SenderAddress;
    final String    _SuccessfulVerb;


    public OkayDGram(Address senderAddress, String successfulVerb) {
        _SenderAddress = senderAddress;
        _SuccessfulVerb = successfulVerb;
    }


    public String export() {
        return  VERB                            + '\n'  +   // Line 1: Own verb!
                _SuccessfulVerb                 + '\n'  +   // Line 2: The line that just succeeded!
                _SenderAddress.hostName()       + '\n'  +   // Line 3: The host-name of the sender
                _SenderAddress.port()           + '\n';     // Line 4: The port of the sender
    }


    public static OkayDGram importFromString(BufferedReader reader) throws IOException {
        String successVerb = reader.readLine();
        String hostName = reader.readLine();
        int port = Integer.parseInt(reader.readLine());

        return new OkayDGram(new Address(hostName, port), successVerb);
    }

}
