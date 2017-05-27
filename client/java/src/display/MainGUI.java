package display; /**
 * Created by ei10117 on 11/05/2017.
 */
import connection.ChatApp;
import display.chatScreen.PanelUsers;
import data.Friend;
import messages.Message;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainGUI {

    String      appName     = "Colt Chat v0.1";
    MainGUI     mainGUI;
    JFrame      newFrame    = new JFrame(appName);
    JButton     sendMessage;
    JTextField  messageBox;
    JTextArea   chatBox;
    JTextField  usernameChooser;
    JFrame      preFrame;
    PanelUsers panelUsers;

    Friend active;

    public MainGUI() {


        chatBox = new JTextArea();
        panelUsers= new PanelUsers();

        panelUsers.refresh(ChatApp.user.getFriends());
        active = ChatApp.user.getFriends().get(1);
        panelUsers.highlightUser(0);
        selectFriend();


    }

    public void selectFriend(){
        panelUsers.getCountryList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if(!e.getValueIsAdjusting()) {
                    final int selectedIndex = panelUsers.getCountryList().getSelectedIndex();
                    active = ChatApp.user.getFriends().get(selectedIndex);
                    fillChatBox();
                }
            }
        });
    }

    public void fillChatBox(){
        chatBox.setText("");
        for(int i = 0; i < active.getMessages().size(); i++)
        {
            chatBox.append(active.messages.get(i));
        }
    }




    public void preDisplay() {
        newFrame.setVisible(false);
        preFrame = new JFrame(appName);


        usernameChooser = new JTextField(15);
        JLabel chooseUsernameLabel = new JLabel("Pick a username:");
        JButton enterServer = new JButton("Enter Chat Server");
        enterServer.addActionListener(new enterServerButtonListener());
        JPanel prePanel = new JPanel(new GridBagLayout());

        GridBagConstraints preRight = new GridBagConstraints();
        preRight.insets = new Insets(0, 0, 0, 10);
        preRight.anchor = GridBagConstraints.EAST;
        GridBagConstraints preLeft = new GridBagConstraints();
        preLeft.anchor = GridBagConstraints.WEST;
        preLeft.insets = new Insets(0, 10, 0, 10);
        // preRight.weightx = 2.0;
        preRight.fill = GridBagConstraints.HORIZONTAL;
        preRight.gridwidth = GridBagConstraints.REMAINDER;

        prePanel.add(chooseUsernameLabel, preLeft);
        prePanel.add(usernameChooser, preRight);
        preFrame.add(BorderLayout.CENTER, prePanel);
        preFrame.add(BorderLayout.SOUTH, enterServer);
        preFrame.setSize(300, 300);
        preFrame.setVisible(true);

    }

    public void display() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();

        sendMessage = new JButton("Send Message");
        sendMessage.addActionListener(new sendMessageButtonListener());


        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);

        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);




        mainPanel.add(BorderLayout.WEST,panelUsers.getAreaScrollPane());
        mainPanel.add(BorderLayout.SOUTH,southPanel);

        newFrame.add(mainPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(700, 600);
        newFrame.setVisible(true);
    }

    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                // do nothing
            } else if (messageBox.getText().equals(".clear")) {
                chatBox.setText("Cleared all messages\n");
                messageBox.setText("");
            } else {
                chatBox.append("<" + username + ">:  " + messageBox.getText()
                        + "\n");
                String str = "<" + username + ">:  " + messageBox.getText()
                        + "\n";

                active.getMessages().add(str);


                DataOutputStream outToServer = null;
                try {
                    outToServer = new DataOutputStream(ChatApp.socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    outToServer.writeBytes(Message.messageTo(active.name,messageBox.getText()) + '\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }

                messageBox.setText("");

            }
            messageBox.requestFocusInWindow();
        }
    }

    String  username;

    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = usernameChooser.getText();
            if (username.length() < 1) {
                System.out.println("No!");
            } else {
                preFrame.setVisible(false);

                display();
            }
        }

    }



}