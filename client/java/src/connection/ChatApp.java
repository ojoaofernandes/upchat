package connection;

import data.User;
import display.MainGUI;

import javax.swing.*;
import java.io.IOException;


/**
 * Created by ei10117 on 26/05/2017.
 */
public class ChatApp {

    public MainGUI mainGUI;
    public ReceiverListener receiverListener;
    public static User user;


    public ChatApp() {
        user = new User("Ruben");
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

    public static void main(String[] args) {
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
