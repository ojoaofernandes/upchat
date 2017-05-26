package display.chatScreen;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import data.Friend;


/**
 * Custom renderer to display a country's flag alongside its name
 *
 * @author wwww.codejava.net
 */
public class FriendRenderer extends JLabel implements ListCellRenderer<Friend> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Friend> list, Friend friend, int index,
                                                  boolean isSelected, boolean cellHasFocus) {


     /*   ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/" + code + ".png"));

        setIcon(imageIcon);
        setText(country.getName());*/
        setBackground(Color.BLACK);

        return this;
    }

}