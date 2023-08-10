package ui;

import model.Application;
import model.ApplicationList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// The starting page of the application
public class StartPanel extends JPanel implements ActionListener {
    private static final String JSON_STORE = "./data/ApplicationManager.json";
    private JPanel startPanel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JButton save;
    private JButton load;
    private JButton add;
    private JButton na;
    private JButton refresh;
    private ApplicationList applicationList;
    private JFrame mainframe;
    private JTable applicationTable;
    private JTextField companyName;
    private JTextField positionName;
    private JTextField status;
    private JTextField deadline;
    private JLabel textLable1;
    private JLabel textLable2;
    private JLabel textLable3;
    private JLabel textLable4;

    // EFFECT: construct the app content
    // MODIFIES: this
    public StartPanel(ApplicationList applicationList, JFrame mainFrame) {
        this.applicationList = applicationList;
        this.mainframe = mainFrame;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadPanel(applicationList);
    }

    // EFFECT: load panel content
    // MODIFIES: this
    private void loadPanel(ApplicationList applicationList) {
        startPanel = this;
        startPanel.setLayout(null);

        initButton();
        initTextField();
        initContentTable(applicationList);
    }

    // EFFECT: load a table to display applications
    // MODIFIES: this
    private void initContentTable(ApplicationList applicationList) {
        HashMap<String, Application> localApplicationList = applicationList.getApplicationList();
        applicationTable = new JTable(localApplicationList.size() + 1, 3);
        applicationTable.setValueAt("Company", 0, 0);
        applicationTable.setValueAt("Position", 0, 1);
        applicationTable.setValueAt("Deadline", 0, 2);

        int row = 1;
        for (Map.Entry<String, Application> entry : localApplicationList.entrySet()) {
            applicationTable.setValueAt(entry.getValue().getCompanyName(), row, 0);
            applicationTable.setValueAt(entry.getValue().getPositionName(), row, 1);
            //applicationTable.setValueAt(entry.getValue().getApplicationStatus(), row, 2);
            if (entry.getValue().getApplicationDeadline() != -1) {
                applicationTable.setValueAt(entry.getValue().getApplicationDeadline(), row, 2);
            } else {
                applicationTable.setValueAt("N/A", row, 2);
            }
            row++;
        }
        applicationTable.setBounds(80, 100, 500, 300);
        startPanel.add(applicationTable);
    }

    // EFFECT: load text fields for adding applications
    // MODIFIES: this
    private void initTextField() {
        companyName = new JTextField();
        positionName = new JTextField();
        deadline = new JTextField();

        companyName.setBounds(800, 100, 100, 25);
        positionName.setBounds(800, 130, 100, 25);
        deadline.setBounds(800, 160, 100, 25);

        startPanel.add(companyName);
        startPanel.add(positionName);
        startPanel.add(deadline);

        //
        textLable1 = new JLabel("Company Name");
        textLable2 = new JLabel("Position Name");
        textLable4 = new JLabel("Deadline");

        textLable1.setBounds(600, 100, 180, 25);
        textLable2.setBounds(600, 130, 180, 25);
        textLable4.setBounds(600, 160, 180, 25);

        startPanel.add(textLable1);
        startPanel.add(textLable2);
        startPanel.add(textLable4);
    }

    // EFFECT: load buttons for app actions
    // MODIFIES: this
    private void initButton() {
        save = new JButton("Save");
        save.setBounds(100, 500, 80, 25);
        save.addActionListener(this);
        startPanel.add(save);

        load = new JButton("Load");
        load.setBounds(250, 500, 80, 25);
        load.addActionListener(this);
        startPanel.add(load);

        add = new JButton("Add");
        add.setBounds(400, 500, 80, 25);
        add.addActionListener(this);
        startPanel.add(add);

        na = new JButton("Show No Deadline");
        na.setBounds(550, 500, 180, 25);
        na.addActionListener(this);
        startPanel.add(na);

        refresh = new JButton(("Refresh"));
        refresh.setBounds(300, 600, 80, 25);
        refresh.addActionListener(this);
        startPanel.add(refresh);
    }

    // EFFECT: load a table to display applications with no deadline
    // MODIFIES: this
    public void loadNA(HashMap<String,Application> localApplicationList) {
        startPanel.remove(applicationTable);
        ArrayList<Application> localNAList = applicationList.applicationsNoDeadline();

        applicationTable = new JTable(localNAList.size() + 1, 3);
        applicationTable.setValueAt("Company", 0, 0);
        applicationTable.setValueAt("Position", 0, 1);
        applicationTable.setValueAt("Deadline", 0, 2);

        int row = 1;
        for (Application application: localNAList) {
            applicationTable.setValueAt(application.getCompanyName(), row, 0);
            applicationTable.setValueAt(application.getPositionName(), row, 1);
            applicationTable.setValueAt("N/A", row, 2);
            row++;
        }
        applicationTable.setBounds(80, 100, 500, 300);
        startPanel.add(applicationTable);
    }

    // EFFECT: monitor and perform actions for app actions
    // MODIFIES: this
    @Override
    public void actionPerformed(ActionEvent evt) {
        saveAndLoad(evt);
        if (evt.getSource() == add) {
            String companyName = this.companyName.getText();
            String positionName = this.positionName.getText();
            //int status = Integer.parseInt(this.status.getText());
            int deadline = Integer.parseInt(this.deadline.getText());
            applicationList.addApplication(new Application(deadline, companyName, positionName));
            JPanel newPanel = new StartPanel(applicationList, mainframe);
            mainframe.setContentPane(newPanel);
        } else if (evt.getSource() == na) {
            loadNA(applicationList.getApplicationList());
            revalidate();
            repaint();
            mainframe.invalidate();
            mainframe.validate();
            mainframe.repaint();
        } else if (evt.getSource() == refresh) {
            JPanel newPanel = new StartPanel(applicationList, mainframe);
            mainframe.setContentPane(newPanel);
        }
    }

    // EFFECT: save and load current applications
    // MODIFIES: this
    private void saveAndLoad(ActionEvent evt) {
        if (evt.getSource() == save) {
            try {
                jsonWriter.open();
                jsonWriter.write(applicationList);
                jsonWriter.close();
                new PopFrame("save");
                System.out.println("Saved to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        } else if (evt.getSource() == load) {
            try {
                applicationList = jsonReader.read();
                JPanel newPanel = new StartPanel(applicationList, mainframe);
                mainframe.setContentPane(newPanel);
                new PopFrame("load");
                System.out.println("Loaded from " + JSON_STORE);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }
}
