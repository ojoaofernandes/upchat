package display;

import connection.ChatApp;
import messages.Message;

/**
 * Created by ei10117 on 11/05/2017.
 */


public class Login {


        public static boolean authenticate(String username, String password) throws InterruptedException {

            String message = Message.loginRequest(username,password);
            Message.sendMessage(message);
            while (!ChatApp.loginReceived){
                Thread.sleep(100);
            }

            while (ChatApp.loginMessage.equals("unchecked")){
                Thread.sleep(100);
            }

            if(ChatApp.loginMessage.equals("LOGIN_SUCCESS"))
                return true;

            ChatApp.loginReceived = false;
            ChatApp.loginMessage = "unchecked";
                return false;
            }
}




