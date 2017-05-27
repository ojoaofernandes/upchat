package connection;

import data.User;
import display.MainGUI;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;


/**
 * Created by ei10117 on 26/05/2017.
 */
public class ChatApp {

    public MainGUI mainGUI;
    public ReceiverListener receiverListener;
    public static User user;
    public static  Socket socket;


    public ChatApp() throws IOException {
        user = new User("Ruben");
        this.socket = new Socket("localhost", 9090);
        user.addFriends();
        try {
            receiverListener = new ReceiverListener();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void runlistener(){
        receiverListener.start();
    }

    public static void main(String[] args) throws IOException {
        ChatApp chatApp = new ChatApp();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager
                            .getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                chatApp.runlistener();
                chatApp.mainGUI = new MainGUI();


                chatApp.mainGUI.preDisplay();
                chatApp.mainGUI.fillChatBox();

            }
        });
    }
}
