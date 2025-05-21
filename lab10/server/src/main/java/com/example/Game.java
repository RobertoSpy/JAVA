package com.example;

import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private String id;
    private HexBoard board = new HexBoard(5); // 5x5 board
    private Player player1;
    private Player player2;
    private String currentTurn;

    private long timeLeftPlayer1 = 10_000; 
    private long timeLeftPlayer2 = 10_000; 

    private Timer timer;
    private TimerTask timerTask;
    private long lastTick;

    public Game(String id, String creator) {
        this.id = id;
        this.player1 = new Player(creator, "X");
        this.currentTurn = creator;
        startTimer();
    }

    public synchronized String addPlayer(String name) {
        if (player2 != null) return "Game full.";
        if (player1.getName().equals(name)) return "Already joined.";
        player2 = new Player(name, "O");
        return "Player " + name + " joined. Game starts!";
    }

    public synchronized String makeMove(String playerName, int row, int col) {
        if (player2 == null) return "Waiting for second player.";
        if (!playerName.equals(currentTurn)) return "Not your turn.";
        Player player = getPlayer(playerName);
        if (player == null) return "Invalid player.";

        updateTimeLeft();

        boolean move = board.setCell(row, col, player.getSymbol());
        if (!move) return "Invalid move.";

        if (board.checkWin(player.getSymbol())) {
            stopTimer();
            return "Player " + playerName + " wins!";
        }

        
        currentTurn = getOpponent(playerName).getName();
        lastTick = System.currentTimeMillis();

        return "Move accepted. Next turn: " + currentTurn + ". Time left - "
            + player1.getName() + ": " + timeLeftPlayer1/1000 + "s, "
            + player2.getName() + ": " + timeLeftPlayer2/1000 + "s";
    }

    private Player getPlayer(String name) {
        if (player1 == null) return null;
        if (player1.getName().equals(name)) return player1;
        if (player2 != null && player2.getName().equals(name)) return player2;
        return null;
    }

    private Player getOpponent(String name) {
        return player1.getName().equals(name) ? player2 : player1;
    }

    private void startTimer() {
        timer = new Timer(true);
        lastTick = System.currentTimeMillis();

        timerTask = new TimerTask() {
            public void run() {
                synchronized (Game.this) {
                    updateTimeLeft();

                    
                    if (timeLeftPlayer1 <= 0) {
                        stopTimer();
                        System.out.println("Player " + player2.getName() + " wins by timeout!");
                    } else if (timeLeftPlayer2 <= 0) {
                        stopTimer();
                        System.out.println("Player " + player1.getName() + " wins by timeout!");
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 100, 100000); 
    }

    private void updateTimeLeft() {
        long now = System.currentTimeMillis();
        long elapsed = now - lastTick;
        lastTick = now;

        if (currentTurn.equals(player1.getName())) {
            timeLeftPlayer1 -= elapsed;
            if (timeLeftPlayer1 < 0) timeLeftPlayer1 = 0;
        } else {
            timeLeftPlayer2 -= elapsed;
            if (timeLeftPlayer2 < 0) timeLeftPlayer2 = 0;
        }
    }

    private void stopTimer() {
        if (timerTask != null) timerTask.cancel();
        if (timer != null) timer.cancel();
    }
}
