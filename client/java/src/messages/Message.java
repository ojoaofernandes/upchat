package messages;

import connection.ChatApp;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by ei10117 on 27/05/2017.
 */
public class Message {
    private static String CRLF = "\\r\\n\\r\\n";


    public static String messageTo(String to, String body){
        return " MESSAGE_TO" + " " + to + " " + CRLF + " " + body;
    }

    public static String loginRequest(String username,String password){
        return "LOGIN_REQUEST" + " " + username + " "  + password + " " + CRLF;
    }



    public static String facebook(){
        return "LOGIN_REQUEST" + " " + "facebook" + " " + "\r\n\r\n";
    }

    public static String twitter(){
        return " LOGIN_REQUEST" + " " + "twitter" + " " + "\r\n\r\n";
    }

    public static String google(){
        return " LOGIN_REQUEST" + " " + "google" + " " + "\r\n\r\n";
    }

    public static void sendMessage(String message){
        DataOutputStream outToServer = null;
        try {
            outToServer = new DataOutputStream(ChatApp.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            outToServer.writeBytes(" " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}