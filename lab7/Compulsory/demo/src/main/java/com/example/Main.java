package com.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Bag bag = new Bag();
        Board board = new Board();
        Dictionary dictionary = new Dictionary("dictionary.txt");
        Object turnLock = new Object();

        List<Thread> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Player player = new Player("Player " + (i + 1), bag, board, dictionary, turnLock, i);
            Thread thread = new Thread(player);
            players.add(thread);
            thread.start();
        }

        Thread timekeeper = new Thread(new Timekeeper(30000, turnLock)); 
        timekeeper.setDaemon(true);
        timekeeper.start();

        for (Thread thread : players) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Game over!");
    }
}