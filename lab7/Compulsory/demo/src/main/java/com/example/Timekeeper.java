package com.example;

public class Timekeeper implements Runnable {
    private final long timeLimitMillis;
    private final Object turnLock;

    public Timekeeper(long timeLimitMillis, Object turnLock) {
        this.timeLimitMillis = timeLimitMillis;
        this.turnLock = turnLock;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeLimitMillis) {
            try {
                Thread.sleep(1000);
                System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime) / 1000 + " seconds");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("Time limit exceeded! Stopping the game...");
        synchronized (turnLock) {
            turnLock.notifyAll();
        }
    }
}