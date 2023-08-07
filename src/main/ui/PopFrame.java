package ui;

import javax.swing.*;

// represent the pop-up window for save/load message
public class PopFrame {
    private JFrame popFrame;
    private JPanel popPanel;
    private ImageIcon saveImage;
    private ImageIcon loadImage;
    private JLabel imageAsLabel;
    private JLabel textLabel;

    // EFFECTS: construct a pop-up window
    // MODIFIES: this
    public PopFrame(String action) {
        popFrame = new JFrame("save/load pop");
        popPanel = new JPanel();
        popFrame.setSize(600, 400);
        popFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        loadImages();

        if (action == "save") {
            save();
        } else if (action == "load") {
            load();
        }

        popFrame.setVisible(true);
        popFrame.setContentPane(popPanel);
        popPanel.updateUI();
    }

    // EFFECT: load the message and image for saving
    // MODIFIES: this
    private void save() {
        removeExistingImage();
        imageAsLabel = new JLabel(saveImage);
        popPanel.add(imageAsLabel);
        textLabel = new JLabel("SAVED for YA");
        textLabel.setBounds(600, 100, 180, 25);
        popPanel.add(textLabel);
    }

    // EFFECT: load the message and image for loading
    // MODIFIES: this
    private void load() {
        removeExistingImage();
        imageAsLabel = new JLabel(loadImage);
        popPanel.add(imageAsLabel);
        textLabel = new JLabel("LOADED for YA");
        textLabel.setBounds(600, 100, 180, 25);
        popPanel.add(textLabel);
    }

    // EFFECT: remove the previous image
    // MODIFIES: this
    private void removeExistingImage() {
        if (imageAsLabel != null) {
            popPanel.remove(imageAsLabel);
        }
    }

    // EFFECT: read the image for saving and loading
    // MODIFIES: this
    private void loadImages() {
        String sep = System.getProperty("file.separator");
        saveImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "save.jpg");
        loadImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "load.jpg");
    }

}
