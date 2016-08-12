package protocol;


import common.Address;
import chat.User;

import java.io.BufferedReader;
import java.io.IOException;

public class HelloDGram extends DGram {

    public static final String VERB = "HELLO";

    final User      _Greeter;
    final String    _Password;


    HelloDGram(User greeter, String password) {
        _Greeter = greeter;
        _Password = password;
    }


    @Override
    public String export() {
        return  VERB                            + '\n'  +       // Line 1: Verb
                _Greeter.address().hostName()   + '\n'  +       // Line 2: Greeter host name
                _Greeter.address().port()       + '\n'  +       // Line 3: Greeter port
                _Greeter.name()                 + '\n'  +       // Line 4: Greeter name
                _Password                       + '\n';         // Line 5: Password for guest
    }


    public static HelloDGram importFromString(BufferedReader reader) throws IOException {
        String hostName = reader.readLine();                // Line 2: Greeter host name
        int port = Integer.parseInt(reader.readLine());     // Line 3: Greeter port
        String userName = reader.readLine();                // Line 4: Greeter name
        String password = reader.readLine();                // Line 5: Password for guest

        return new HelloDGram(new User(userName, new Address(hostName, port)), password);
    }


    public User greeter()      { return _Greeter; }
    public String password()   { return _Password; }

}
