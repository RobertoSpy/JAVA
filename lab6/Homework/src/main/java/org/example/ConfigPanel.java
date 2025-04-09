package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner spinner;
    JButton newGameBtn;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {

        label = new JLabel("Number of dots:");
        spinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));  


        newGameBtn = new JButton("New Game");
        newGameBtn.addActionListener(this::startNewGame);


        setLayout(new FlowLayout());
        add(label);
        add(spinner);
        add(newGameBtn);
    }


    private void startNewGame(ActionEvent e) {
        int numDots = (int) spinner.getValue();
        frame.getCanvas().startNewGame(numDots);
    }
}
