package display.chatScreen;

import data.Friend;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by ei10117 on 19/05/2017.
 */
public class PanelUsers {
    private JList<Friend> listComponent;

    public JScrollPane areaScrollPane;
    public DefaultListModel<Friend> friendsModel;
    public PanelUsers() {

        friendsModel = new DefaultListModel<Friend>();
        listComponent = new JList<Friend>(friendsModel);
        areaScrollPane = new JScrollPane(listComponent);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(125, 250));

        listComponent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


    }


    public void refresh(ArrayList<Friend> friends){
        friendsModel.clear();
        for (Iterator<Friend> i = friends.iterator(); i.hasNext();) {
            Friend friend = i.next();
            friendsModel.addElement(friend);

        }
    }

    public JScrollPane getAreaScrollPane() {
        return areaScrollPane;
    }

    public void setAreaScrollPane(JScrollPane areaScrollPane) {
        this.areaScrollPane = areaScrollPane;
    }

    public JList<Friend> getCountryList() {
        return listComponent;
    }

    public void setCountryList(JList<Friend> countryList) {
        this.listComponent = countryList;
    }

    public void highlightUser(int index){
        new FriendRenderer().getListCellRendererComponent(listComponent,friendsModel.elementAt(index),index,false,true);


    }
}

