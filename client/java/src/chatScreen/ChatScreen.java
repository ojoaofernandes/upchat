package chatScreen;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ei10117 on 19/05/2017.
 */
public class ChatScreen {

    public static void main(String[] args) {
        final JFrame frame = new JFrame("chatScreen.ChatScreen");
        final PanelUsers panelUsers= new PanelUsers();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());



      /*  areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));*/
        frame.getContentPane().add(panelUsers.getAreaScrollPane());


        frame.setVisible(true);
    }

}
