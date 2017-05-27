package messages;

/**
 * Created by ei10117 on 27/05/2017.
 */
public class Message {


    public static String messageTo(String to, String body){
        return "MESSAGE_TO" + " " + to + " " + "\r\n\r\n" + " " + body;
    }

    public static String facebook(){
        return "LOGIN_REQUEST" + " " + "facebook" + " " + "\r\n\r\n";
    }

    public static String twitter(){
        return "LOGIN_REQUEST" + " " + "twitter" + " " + "\r\n\r\n";
    }

    public static String google(){
        return "LOGIN_REQUEST" + " " + "google" + " " + "\r\n\r\n";
    }





}
