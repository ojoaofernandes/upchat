package display; /**
 * Created by ei10117 on 11/05/2017.
 */
import java.awt.*;
import java.awt.event.*;

import java.net.URI;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("JDialog Demo");
        final JButton btnLogin = new JButton("Click to login");
        final JButton faceLogin = new JButton("Facebook");
        final JButton twitterLogin = new JButton("Twitter");
        final JButton googleLogin = new JButton("Google+");

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


        faceLogin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                            try {
                                desktop.browse(new URI("http://127.0.0.1:3000/login/facebook"));
                            } catch (Exception exp) {
                                exp.printStackTrace();
                            }
                        }
                    }
                });

        twitterLogin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                            try {
                                desktop.browse(new URI("http://127.0.0.1:3000/login/twitter"));
                            } catch (Exception exp) {
                                exp.printStackTrace();
                            }
                        }
                    }
                });

        googleLogin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                            try {
                                desktop.browse(new URI("http://127.0.0.1:3000/auth/google"));
                            } catch (Exception exp) {
                                exp.printStackTrace();
                            }
                        }
                    }
                });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(btnLogin);
        frame.getContentPane().add(faceLogin);
        frame.getContentPane().add(twitterLogin);
        frame.getContentPane().add(googleLogin);
        frame.setVisible(true);
    }


}