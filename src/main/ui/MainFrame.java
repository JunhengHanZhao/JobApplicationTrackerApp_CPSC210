package ui;

import model.Application;
import model.ApplicationList;
import model.EventLog;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.util.Iterator;

// represent the main window frame of the application
public class MainFrame {
    private JFrame mainFrame;
    private ApplicationList applicationList;

    // EFFECT: construct the main window
    // MODIFIES: This
    public MainFrame() {
        init();

        mainFrame = new JFrame("Application Manager App");
        mainFrame.setSize(1280, 720);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Iterator eventIterator = EventLog.getInstance().iterator();

                while (eventIterator.hasNext()) {
                    System.out.println(eventIterator.next());
                }

                System.exit(0);
            }
        });

        mainFrame.setVisible(true);

        StartPanel startPanel = new StartPanel(applicationList, mainFrame);
        mainFrame.setContentPane(startPanel);
        startPanel.updateUI();
    }

    // EFFECT: initialize application with some existing application cases
    // MODIFIES: this
    private void init() {
        EventLog.getInstance().clear();
        applicationList = new ApplicationList();
        applicationList.addApplication(
                new Application(-1, "Test Company 1", "Test Position 1"));
        applicationList.addApplication(
                new Application(20, "Test Company 2", "Test Position 2"));
    }
}
