package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket socket;
    private final GameServer server;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try (
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String request;
            while ((request = input.readLine()) != null) {
                System.out.println("Received command: " + request);

                if ("exit".equalsIgnoreCase(request)) {
                    output.println("Server stopped");
                    server.stop(); 
                    break;
                } else {
                    output.println("Server received the request: " + request);
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error with client: " + e.getMessage());
        }
    }
}
