package ui;

import model.Application;
import model.ApplicationList;

import javax.swing.*;

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
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setVisible(true);

        StartPanel startPanel = new StartPanel(applicationList, mainFrame);
        mainFrame.setContentPane(startPanel);
        startPanel.updateUI();
    }

    // EFFECT: initialize application with some existing application cases
    // MODIFIES: this
    private void init() {
        applicationList = new ApplicationList();
        applicationList.addApplication(
                new Application(-1, "Test Company 1", "Test Position 1"));
        applicationList.addApplication(
                new Application(20, "Test Company 2", "Test Position 2"));
    }
}
