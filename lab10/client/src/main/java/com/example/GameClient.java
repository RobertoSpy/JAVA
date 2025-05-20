package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            System.out.println("Connected to server. Type commands:");
            String request;
            while ((request = keyboard.readLine()) != null) {
                output.println(request);
                String response = input.readLine();
                System.out.println("Server replied: " + response);

                if ("exit".equalsIgnoreCase(request)) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
