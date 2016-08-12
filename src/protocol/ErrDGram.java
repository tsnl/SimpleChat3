package protocol;


import common.Address;

import java.io.BufferedReader;
import java.io.IOException;

public class ErrDGram extends DGram {

    public static final String VERB = "ERROR";

    final Address _Sender;
    final String  _ErrMessage;


    public ErrDGram(Address sender, String errorMessage) {
        _Sender = sender;
        _ErrMessage = errorMessage;
    }


    @Override
    public String export() {
        return  VERB                            + '\n'  +   // Line 1: Verb
                _Sender.hostName()              + '\n'  +   // Line 2: Sender's host-name
                _Sender.port()                  + '\n'  +   // Line 3: Sender's port-number
                _ErrMessage.split("\n").length  + '\n'  +   // Line 4: Number of lines (N)
                _ErrMessage                     + '\n';     // Line [5, N]: Message
    }


    public static ErrDGram importFromString(BufferedReader reader) throws IOException {
        String hostName = reader.readLine();
        int port = Integer.parseInt(reader.readLine());

        int numLines = Integer.parseInt(reader.readLine());
        StringBuilder errorMessageBuilder = new StringBuilder();
        for (int i = 0; i < numLines; ++i) {
            errorMessageBuilder.append(reader.readLine());
        }

        return new ErrDGram(new Address(hostName, port), errorMessageBuilder.toString());
    }

}
