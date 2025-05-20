package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static final int PORT = 1234;
    private boolean running = true;

    public static void main(String[] args) {
        new GameServer().start();
    }

    public void start() {
        System.out.println("Server started on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (running) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket);
                new ClientThread(socket, this).start();
            }
        } catch (IOException e) {
            System.err.println("Error in GameServer: " + e.getMessage());
        }
    }

    public void stop() {
        this.running = false;
        System.out.println("Server is stopping...");
    }
}
