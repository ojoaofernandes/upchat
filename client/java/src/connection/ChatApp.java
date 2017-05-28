package connection;

import data.User;
import display.LoginDialog;
import display.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;


/**
 * Created by ei10117 on 26/05/2017.
 */
public class ChatApp {

    public static MainGUI mainGUI;
    public LoginDialog loginDialog;
    public ReceiverListener receiverListener;
    public static User user;
    public static boolean loginReceived;
    public static String loginMessage;
    public static  Socket socket;
    public static  JFrame frame;


    public ChatApp() throws IOException {

        user = new User();
        frame = new JFrame("App chat");
        loginReceived = false;
        loginMessage = "unckecked";
        this.socket = new Socket("192.168.108.180", 5570);

        try {
            receiverListener = new ReceiverListener(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void loginSuccess(){

        loginReceived = true;
        loginMessage = "LOGIN_SUCCESS";
    }

    public static void loginError() {
        loginReceived = true;
        loginMessage = "LOGIN_ERROR";
    }

    public void runlistener(){
        receiverListener.start();
    }

    public static void updateList(){
        mainGUI.updatePanelUsers();
    }

    public static void main(String[] args) throws IOException {
        ChatApp chatApp = new ChatApp();

        final JButton btnLogin = new JButton("Click to login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(btnLogin);

        btnLogin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        LoginDialog loginDlg = new LoginDialog(frame);
                        loginDlg.setVisible(true);
                        // if logon successfully
                        if(loginDlg.isSucceeded()){
                            btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
                        }
                    }
                });

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

                JButton btnLogin = new JButton("Click to login");
                chatApp.loginDialog = new LoginDialog(frame);


               /* frame.getContentPane().add(faceLogin);
                frame.getContentPane().add(twitterLogin);
                frame.getContentPane().add(googleLogin);*/
                frame.setVisible(true);

            }
        });
    }


}
