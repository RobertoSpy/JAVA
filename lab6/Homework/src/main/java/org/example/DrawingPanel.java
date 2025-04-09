package org.example;
import org.example.GameLogic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.example.Line;

import java.util.Random;

public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    private int canvasWidth = 400, canvasHeight = 400;
    private BufferedImage image;
    private Graphics2D offscreen;
    private List<Dot> dots = new ArrayList<>();
    private List<Line> lines = new ArrayList<>();
    private int dotSize = 20;
    private Dot selectedDot = null;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        initMouseListeners();
    }

    public List<Dot> getDots() {
        return dots;
    }
    
    public List<Line> getLines() {
        return lines;
    }
    
    public List<Line> getPlayer1Lines() {
        return player1Lines;
    }
    
    public List<Line> getPlayer2Lines() {
        return player2Lines;
    }

    private void createOffscreenImage() {
        image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        offscreen = image.createGraphics();
        clearCanvas();
    }

    private void clearCanvas() {
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(image, 0, 0, this);
    }

    public void startNewGame(int numDots) {
        Random rand = new Random();
        dots.clear();
        lines.clear();
        clearCanvas();

        for (int i = 0; i < numDots; i++) {
            int x = rand.nextInt(canvasWidth - dotSize) + dotSize / 2;
            int y = rand.nextInt(canvasHeight - dotSize) + dotSize / 2;
            dots.add(new Dot(x, y));
        }

        paintDots();
        repaint();
    }

    private void paintDots() {
        offscreen.setColor(Color.RED);
        for (Dot dot : dots) {
            offscreen.fillOval(dot.getX() - dotSize / 2, dot.getY() - dotSize / 2, dotSize, dotSize);
        }
    }

    private void paintLines() {
        for (Line line : player1Lines) {
            offscreen.setColor(Color.BLACK);
            offscreen.drawLine(line.getStart().getX(), line.getStart().getY(),
                               line.getEnd().getX(), line.getEnd().getY());
        }
        for (Line line : player2Lines) {
            offscreen.setColor(Color.YELLOW); 
            offscreen.drawLine(line.getStart().getX(), line.getStart().getY(),
                               line.getEnd().getX(), line.getEnd().getY());
        }
    }

    private int currentPlayer = 1; 
    private List<Line> player1Lines = new ArrayList<>();
    private List<Line> player2Lines = new ArrayList<>();
    
    private void initMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Dot clickedDot = findDotAt(e.getX(), e.getY());
                if (clickedDot != null) {
                    if (selectedDot == null) {
                        selectedDot = clickedDot;
                    } else {
                        // Adaugă linia trasată
                        Line newLine = new Line(selectedDot, clickedDot);
                        if (currentPlayer == 1) {
                            player1Lines.add(newLine);
                        } else {
                            player2Lines.add(newLine);
                        }
                        lines.add(newLine);
                        selectedDot = null;
    
                        
                        repaintCanvas();
    
                        
                        calculateAndDisplayScores();
    
                        
                        currentPlayer = (currentPlayer == 1) ? 2 : 1;
                    }
                }
            }
        });
    }

    private void calculateAndDisplayScores() {
        GameLogic gameLogic = new GameLogic();
        int player1Score = gameLogic.calculateMSTScore(player1Lines, dots);
        int player2Score = gameLogic.calculateMSTScore(player2Lines, dots);
        int bestScore = gameLogic.calculateBestScore(dots);
    
        String player1Message = (player1Score == -1) ? "Incomplete MST" : String.valueOf(player1Score);
        String player2Message = (player2Score == -1) ? "Incomplete MST" : String.valueOf(player2Score);
    
        JOptionPane.showMessageDialog(this,
            "Player 1 Score (MST): " + player1Message + "\n" +
            "Player 2 Score (MST): " + player2Message + "\n" +
            "Best Possible Score: " + bestScore,
            "Current Scores",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private Dot findDotAt(int x, int y) {
        for (Dot dot : dots) {
            int dx = dot.getX() - x;
            int dy = dot.getY() - y;
            if (Math.sqrt(dx * dx + dy * dy) <= dotSize / 2) {
                return dot;
            }
        }
        return null;
    }

    private void repaintCanvas() {
        clearCanvas(); 
        paintDots(); 
        paintLines(); 
        repaint(); 
    }

    public void saveAsImage(String fileName) {
        try {
            ImageIO.write(image, "PNG", new File(fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void saveGame(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(dots);
            out.writeObject(lines);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadGame(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            dots = readObject(in, Dot.class);
            lines = readObject(in, Line.class);
            repaintCanvas();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> readObject(ObjectInputStream in, Class<T> clazz) throws IOException, ClassNotFoundException {
        return (List<T>) in.readObject();
    }



    public void compareScores() {
        GameLogic gameLogic = new GameLogic();
        int playerScore = gameLogic.calculateScore(lines);
        int bestScore = gameLogic.calculateBestScore(dots);
    
        JOptionPane.showMessageDialog(this,
            "Player Score: " + playerScore + "\nBest Possible Score: " + bestScore,
            "Score Comparison",
            JOptionPane.INFORMATION_MESSAGE);
    }

    public void startNewGame() {
        dots.clear(); 
        lines.clear(); 
        player1Lines.clear(); 
        player2Lines.clear(); 
        selectedDot = null; 
        currentPlayer = 1; 
        clearCanvas(); 
        repaintCanvas(); 
    }
    
}