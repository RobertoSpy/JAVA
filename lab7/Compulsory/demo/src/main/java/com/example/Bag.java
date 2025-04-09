package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Bag {
    private final List<Character> tiles = new ArrayList<>();

    public Bag() {
        for (char c = 'A'; c <= 'Z'; c++) {
            for (int i = 0; i < 10; i++) {
                tiles.add(c);
            }
        }
        Collections.shuffle(tiles);
    }

    public synchronized List<Character> extractTiles(int count) {
        if (tiles.size() < count) {
            return new ArrayList<>();
        }
        List<Character> extracted = new ArrayList<>(tiles.subList(0, count));
        tiles.subList(0, count).clear();
        return extracted;
    }

    public synchronized boolean isEmpty() {
        return tiles.isEmpty();
    }
}