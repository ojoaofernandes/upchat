package handlers;

import connection.ChatApp;
import messages.ParserHeader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ei10117 on 26/05/2017.
 */
public class ReceiveHandler extends Thread {

    private final ArrayList<String> fields;

    public ReceiveHandler(ArrayList<String> fields ) {
        this.fields = fields;
    }

    @Override
    public void run() {

        String header  = fields.get(0);
        String body = fields.get(2);
        String[] fields = header.split(" ");

        String type = fields[0];

        System.out.println("typE:" +  type);

        if(type.equals("MESSAGE_FROM")){
            String from = fields[1];

            String message = "<" + from + ">:  " + body
                    + "\n";
            System.out.println(message);
            ChatApp.user.updateMessageBox(from,message);
        }

    }
}
