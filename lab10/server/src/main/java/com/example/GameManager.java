package com.example;

import java.util.*;

public class GameManager {
    private Map<String, Game> games = new HashMap<>();

    public synchronized String handleCommand(String command) {
        String[] tokens = command.trim().split("\\s+");
        if (tokens.length < 1) return "Invalid command.";

        switch (tokens[0].toLowerCase()) {
            case "create":
                if (tokens.length != 4) return "Usage: create game <gameId> <playerName>";
                String gameId = tokens[2];
                String creator = tokens[3];
                Game game = new Game(gameId, creator);
                games.put(gameId, game);
                return "Game created.";

            case "join":
                if (tokens.length != 4) return "Usage: join game <gameId> <playerName>";
                gameId = tokens[2];
                String player = tokens[3];
                game = games.get(gameId);
                if (game == null) return "Game not found.";
                return game.addPlayer(player);

            case "move":
                if (tokens.length != 5) return "Usage: move <gameId> <playerName> <row> <col>";
                gameId = tokens[1];
                player = tokens[2];
                int row = Integer.parseInt(tokens[3]);
                int col = Integer.parseInt(tokens[4]);
                game = games.get(gameId);
                if (game == null) return "Game not found.";
                return game.makeMove(player, row, col);

            default:
                return "Unknown command.";
        }
    }
}
