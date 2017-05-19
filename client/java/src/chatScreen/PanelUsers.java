package chatScreen;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ei10117 on 19/05/2017.
 */
public class PanelUsers {
    private JList<String> countryList;
    public JScrollPane areaScrollPane;

    public PanelUsers() {

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("USA");
        listModel.addElement("India");
        listModel.addElement("Vietnam");
        listModel.addElement("Canada");
        listModel.addElement("Denmark");
        listModel.addElement("France");
        listModel.addElement("Great Britain");
        listModel.addElement("Japan");

        countryList = new JList<>(listModel);


        areaScrollPane = new JScrollPane(countryList);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(125, 250));
    }

    public void changeText(String texto){

    }

    public JScrollPane getAreaScrollPane() {
        return areaScrollPane;
    }

    public void setAreaScrollPane(JScrollPane areaScrollPane) {
        this.areaScrollPane = areaScrollPane;
    }
}

/*
public class PanelUsers extends JFrame {
    private JList<String> countryList;
    public PanelUsers() {
        //create the model and add elements
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("USA");
        listModel.addElement("India");
        listModel.addElement("Vietnam");
        listModel.addElement("Canada");
        listModel.addElement("Denmark");
        listModel.addElement("France");
        listModel.addElement("Great Britain");
        listModel.addElement("Japan");

        //create the list
        countryList = new JList<>(listModel);
        add(countryList);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JList Example");
        this.setSize(200,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PanelUsers();
            }
        });
    }
}*/
