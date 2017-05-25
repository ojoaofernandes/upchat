package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ei10117 on 25/05/2017.
 */
public class User {
    public ArrayList<Friend> friends ;
    public String name;

    public User(String name) {
        this.name = name;
        friends = new ArrayList<>();
    }

    public void addFriends()
    {
        friends.add(new Friend("Ruben"));
        friends.add(new Friend("Ze"));
        friends.add(new Friend("Sal"));

    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
