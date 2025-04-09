package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton exportBtn = new JButton("Export");
    JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame; 
        init(); 
    }

    private void init() {
        setLayout(new GridLayout(1, 4)); // 4 butoane pe un rând

        add(loadBtn);
        add(saveBtn);
        add(exportBtn);
        add(exitBtn);

        exitBtn.addActionListener(this::exitGame);

        loadBtn.addActionListener(e -> loadGame());
        saveBtn.addActionListener(e -> saveGame());
        exportBtn.addActionListener(e -> exportImage());
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

    private void loadGame() {
        try {
            File file = new File("game.ser");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(frame, "No saved game found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            frame.getCanvas().loadGame("game.ser");
            JOptionPane.showMessageDialog(frame, "Game loaded successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Failed to load game: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveGame() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("game.ser"))) {
            DrawingPanel canvas = frame.getCanvas();
            if (canvas == null) {
                JOptionPane.showMessageDialog(this, "Canvas is not initialized. Cannot save.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<Dot> dots = canvas.getDots();
            List<Line> lines = canvas.getLines();
            List<Line> player1Lines = canvas.getPlayer1Lines();
            List<Line> player2Lines = canvas.getPlayer2Lines();

            if (dots == null || lines == null || player1Lines == null || player2Lines == null) {
                JOptionPane.showMessageDialog(this, "Game state is invalid. Cannot save.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            out.writeObject(dots);
            out.writeObject(lines);

            GameLogic gameLogic = new GameLogic();
            int player1Score = gameLogic.calculateMSTScore(player1Lines, dots);
            int player2Score = gameLogic.calculateMSTScore(player2Lines, dots);
            int bestScore = gameLogic.calculateBestScore(dots);

            JOptionPane.showMessageDialog(this,
                "Player 1 Score (MST): " + player1Score + "\n" +
                "Player 2 Score (MST): " + player2Score + "\n" +
                "Best Possible Score: " + bestScore,
                "Game Saved",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save game: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exportImage() {
        try {
            frame.getCanvas().saveAsImage("game.png");
            JOptionPane.showMessageDialog(frame, "Game board exported as PNG!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Failed to export image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}