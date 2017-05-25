package data;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by ei10117 on 25/05/2017.
 */
public class Friend {

    public String name;
    public LinkedList<String> mensagens;

    public Friend(String name) {
        this.name = name;
        mensagens = new LinkedList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<String> getMensagens() {
        return mensagens;
    }

    public void setMensagens(LinkedList<String> mensagens) {
        this.mensagens = mensagens;
    }
}
