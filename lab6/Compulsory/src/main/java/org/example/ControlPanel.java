package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 3));  // 3 butoane pe un rând


        add(loadBtn);
        add(saveBtn);
        add(exitBtn);


        exitBtn.addActionListener(this::exitGame);


        loadBtn.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Load function not implemented yet."));
        saveBtn.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Save function not implemented yet."));
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
}
