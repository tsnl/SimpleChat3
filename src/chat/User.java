package chat;

import common.Address;


public class User {

    private final String        _Name;
    private final Address       _Address;


    public User(String name, Address address) {
        _Name = name;
        _Address = address;
    }


    public String name() { return _Name; }
    public Address address() { return _Address; }

}
