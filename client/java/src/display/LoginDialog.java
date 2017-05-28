package display; /**
 * Created by ei10117 on 11/05/2017.
 */
import connection.ChatApp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class LoginDialog extends JDialog {

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;

    public LoginDialog(Frame parent) {
        super(parent, "display.Login", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);

        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("display.Login");

        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (Login.authenticate(getUsername(), getPassword())) {
                        JOptionPane.showMessageDialog(LoginDialog.this,
                                "Hi " + getUsername() + "! You have successfully logged in.",
                                "display.Login",
                                JOptionPane.INFORMATION_MESSAGE);
                        succeeded = true;

                        dispose();
                        ChatApp.frame.setVisible(false);
                        System.out.println(getUsername());

                        ChatApp.user.setName(getUsername());
                        ChatApp.mainGUI = new MainGUI();
                        ChatApp.mainGUI.newFrame.setTitle(getUsername());
                        System.out.println(ChatApp.user.getFriends().size());
                        ChatApp.mainGUI.panelUsers.refresh(ChatApp.user.getFriends());
                        ChatApp.mainGUI.preDisplay();
                       // ChatApp.mainGUI.fillChatBox();

                    } else {
                        JOptionPane.showMessageDialog(LoginDialog.this,
                                "Invalid username or password",
                                "display.Login",
                                JOptionPane.ERROR_MESSAGE);
                        // reset username and password
                        tfUsername.setText("");
                        pfPassword.setText("");
                        succeeded = false;

                    }
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public String getUsername() {
        return tfUsername.getText().trim();
    }

    public String getPassword() {
        return new String(pfPassword.getPassword());
    }

    public boolean isSucceeded() {
        return succeeded;
    }


}