package com.example;

import java.util.List;
import java.util.Random;

public class Player implements Runnable {
    private final String name;
    private final Bag bag;
    private final Board board;
    private final Dictionary dictionary;
    private final Object turnLock;
    private static int currentTurn = 0;
    private final int playerId;
    private int score = 0;

    public Player(String name, Bag bag, Board board, Dictionary dictionary, Object turnLock, int playerId) {
        this.name = name;
        this.bag = bag;
        this.board = board;
        this.dictionary = dictionary;
        this.turnLock = turnLock;
        this.playerId = playerId;
    }

    @Override
    public void run() {
        while (!bag.isEmpty()) {
            synchronized (turnLock) {
                while (currentTurn != playerId) {
                    try {
                        turnLock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                List<Character> tiles = bag.extractTiles(7);
                if (tiles.isEmpty()) {
                    currentTurn = (currentTurn + 1) % 4;
                    turnLock.notifyAll();
                    break;
                }

                String word = createWord(tiles);
                if (word != null && dictionary.isValidWord(word)) {
                    score += board.submitWord(this, word, tiles);
                }

                currentTurn = (currentTurn + 1) % 4;
                turnLock.notifyAll();
            }
        }
        System.out.println(name + " finished with score: " + score);
    }

    private String createWord(List<Character> tiles) {
        
        StringBuilder availableLetters = new StringBuilder();
        for (Character tile : tiles) {
            availableLetters.append(tile);
        }
    
        
        for (String word : dictionary.getWords()) {
            if (canFormWord(word, availableLetters.toString())) {
                return word; 
            }
        }
    
        return null; 
    }
    
    
    private boolean canFormWord(String word, String availableLetters) {
        StringBuilder letters = new StringBuilder(availableLetters);
        for (char c : word.toCharArray()) {
            int index = letters.indexOf(String.valueOf(c));
            if (index == -1) {
                return false; 
            }
            letters.deleteCharAt(index); 
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}