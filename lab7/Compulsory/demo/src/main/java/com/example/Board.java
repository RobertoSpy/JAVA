package com.example;

import java.util.List;

public class Board {
    public synchronized void submitWord(Player player, String word) {
        System.out.println(player.getName() + " submitted the word: " + word);
    }
}