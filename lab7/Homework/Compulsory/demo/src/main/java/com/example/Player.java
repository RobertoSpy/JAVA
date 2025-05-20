package com.example;

import java.util.List;
import java.util.Random;

public class Player implements Runnable {
    private final String name;
    private final Bag bag;
    private final Board board;

    public Player(String name, Bag bag, Board board) {
        this.name = name;
        this.bag = bag;
        this.board = board;
    }

    @Override
    public void run() {
        while (!bag.isEmpty()) {
            List<Character> tiles = bag.extractTiles(7);
            if (tiles.isEmpty()) {
                break;
            }
            String word = createWord(tiles);
            if (word != null) {
                board.submitWord(this, word); 
            }
        }
        System.out.println(name + " has finished.");
    }

    private String createWord(List<Character> tiles) {
        
        Random random = new Random();
        if (random.nextBoolean()) {
            StringBuilder word = new StringBuilder();
            for (Character tile : tiles) {
                word.append(tile);
            }
            return word.toString();
        }
        return null; 
    }

    public String getName() {
        return name;
    }
}