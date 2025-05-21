package com.example;

import java.io.*;
import java.net.Socket;

public class GameClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 1234;

        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true)
        ) {
            System.out.println("Connected. Type your commands:");
            String command;
            while ((command = keyboard.readLine()) != null) {
                output.println(command);
                String response = input.readLine();
                System.out.println("Server: " + response);
            }
        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
