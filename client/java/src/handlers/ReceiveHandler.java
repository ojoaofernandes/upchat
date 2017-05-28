package handlers;

import connection.ChatApp;

import java.util.ArrayList;

/**
 * Created by ei10117 on 26/05/2017.
 */
public class ReceiveHandler extends Thread {

    private final ArrayList<String> message;

    public ReceiveHandler(ArrayList<String> message ) {
        this.message = message;
    }

    @Override
    public void run() {

        String header  = message.get(0);

        String[] fields = header.split(" ");
        String type;
        if(fields.length == 3){
            type = fields[2];
        }
        else
        type = fields[0];

        System.out.println("type:" + type);



        if(type.equals("MESSAGE_FROM")){
            String from = fields[1];
            String body = message.get(2);
            String message = "<" + from + ">:  " + body
                    + "\n";
           // System.out.println(message);
            ChatApp.user.updateMessageBox(from,message);
            ChatApp.mainGUI.fillChatBox();
        }
        else if(type.equals("LOGOUT")){
            //close gui
        }
        else if(type.equals("UPDATE_FRIENDS")){
            System.out.println("entrou");
            String[] friendsList = null;
            if(message.size() == 3) {
                friendsList = message.get(2).split(" ");

            }
            ChatApp.user.updateFriends(friendsList);
            System.out.println(ChatApp.user.getFriends().size());
            ChatApp.updateList();
        }

        else if(type.equals("LOGIN_SUCCESS")){

            ChatApp.loginSuccess();
            String[] friendsList = null;
            if(message.size() == 3) {
                friendsList = message.get(2).split(" ");

            }
            ChatApp.user.addFriends(friendsList);

        }else if(type.equals("LOGIN_ERROR")){
            System.out.println("entrou");
            ChatApp.loginError();
        }


    }
}
