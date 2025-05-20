package com.example;

import java.util.List;

public class Board {
    public synchronized int submitWord(Player player, String word, List<Character> tiles) {
        int score = tiles.size(); 
        System.out.println(player.getName() + " submitted the word: " + word + " for " + score + " points.");
        return score;
    }
}