package protocol;


import common.Address;
import chat.User;

import java.io.BufferedReader;
import java.io.IOException;

public class MessageDGram extends DGram {

    public final static String VERB = "MESSAGE";

    final User      _Sender;
    final String    _Contents;

    MessageDGram(User sender, String contents) {
        _Sender = sender;
        _Contents = contents;
    }


    @Override
    public String export() {
        return  VERB                            + '\n'  +       // Line 1: Verb
                _Sender.name()                  + '\n'  +       // Line 2: Sender's name
                _Sender.address().hostName()    + '\n'  +       // Line 3: Sender's host-name
                _Sender.address().port()        + '\n'  +       // Line 4: Sender's port
                _Contents.split("\n").length    + '\n'  +       // Line 5: Number of lines of text (N)
                _Contents                       + '\n';         // Line [6, 5 + N]: Message
    }


    public static MessageDGram importFromString(BufferedReader reader) throws IOException {
        String userName = reader.readLine();
        String hostName = reader.readLine();                    // Line 2: Sender's host name
        int port = Integer.parseInt(reader.readLine());         // Line 3: Sender's port
        int numLines = Integer.parseInt(reader.readLine());     // Line 4: Number of lines of text in the message

        StringBuilder contentsBuilder = new StringBuilder();
        for (int i = 0; i < numLines; ++i)
            contentsBuilder.append(reader.readLine());

        return new MessageDGram(new User(userName, new Address(hostName, port)), contentsBuilder.toString());
    }

}
