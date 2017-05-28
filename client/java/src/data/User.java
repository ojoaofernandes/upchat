package data;

import connection.ChatApp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ei10117 on 25/05/2017.
 */
public class User {
    public ArrayList<Friend> friends ;
    public String name;

    public User() {

        friends = new ArrayList<>();
    }

    public void addFriends(String[] list)
    {
        if(list != null) {
            for (int i = 0; i < list.length; i++) {
                friends.add(new Friend(list[i]));
            }
        }
    }

    public void updateFriends(String[] list)
    {

        if(list != null) {
            ArrayList<Friend> aux =  new ArrayList<>();

            for (int i = 0; i < list.length; i++) {
                aux.add(new Friend(list[i]));
            }
            if(aux != friends)
                friends = aux;
            ChatApp.updateList();
        }

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

    public int friendExists(String name){
        for (int i= 0;i < friends.size(); i++)
        {
            System.out.println(friends.get(i).getName() + " "+  (name));
            if(friends.get(i).getName().equals(name))
                return i;
        }
        return -1;
    }


    public void updateMessageBox(String name, String message){
        int ret = friendExists(name);
        System.out.println(ret);
        if(ret != -1)
            friends.get(ret).addMessage(message);;


    }
}
