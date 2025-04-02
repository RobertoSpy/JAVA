package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    private int canvasWidth = 400, canvasHeight = 400;
    private BufferedImage image;
    private Graphics2D offscreen;
    private List<Dot> dots = new ArrayList<>();
    private int dotSize = 20;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
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

    // Metodă pentru a începe un nou joc
    public void startNewGame(int numDots) {
        Random rand = new Random();
        dots.clear();
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
}
