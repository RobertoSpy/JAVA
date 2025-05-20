package com.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Bag bag = new Bag();
        Board board = new Board();

        List<Thread> players = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Player player = new Player("Player " + i, bag, board);
            Thread thread = new Thread(player);
            players.add(thread);
            thread.start();
        }

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