package data;

import java.util.LinkedList;

/**
 * Created by ei10117 on 25/05/2017.
 */
public class Friend {

    public String name;
    public LinkedList<String> messages;

    public Friend(String name) {
        this.name = name;
        messages = new LinkedList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<String> getMessages() {
        return messages;
    }

    public void setMessages(LinkedList<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message){
        messages.add(message);
    }

    @Override
    public String toString() {
        return name;
    }
}
