package chatScreen;

import data.Friend;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by ei10117 on 19/05/2017.
 */
public class PanelUsers {
    private JList<String> countryList;

    public JScrollPane areaScrollPane;
    public DefaultListModel<String> listModel;
    public PanelUsers() {

        listModel = new DefaultListModel<>();
        countryList = new JList<>(listModel);
        areaScrollPane = new JScrollPane(countryList);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(125, 250));

        countryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


    }

    public void refresh(ArrayList<Friend> friends){
       // listModel.clear();
        for (Iterator<Friend> i = friends.iterator(); i.hasNext();) {
            Friend friend = i.next();
            listModel.addElement(friend.getName());

            System.out.printf(friend.getName());
        }
    }

    public JScrollPane getAreaScrollPane() {
        return areaScrollPane;
    }

    public void setAreaScrollPane(JScrollPane areaScrollPane) {
        this.areaScrollPane = areaScrollPane;
    }

    public JList<String> getCountryList() {
        return countryList;
    }

    public void setCountryList(JList<String> countryList) {
        this.countryList = countryList;
    }
}

