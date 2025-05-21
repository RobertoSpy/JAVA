package com.example;

public class HexBoard {
    private String[][] board;
    private int size;

    public HexBoard(int size) {
        this.size = size;
        board = new String[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                board[i][j] = ".";
    }

    public boolean setCell(int row, int col, String symbol) {
        if (row < 0 || col < 0 || row >= size || col >= size || !board[row][col].equals(".")) {
            return false;
        }
        board[row][col] = symbol;
        return true;
    }

    public boolean checkWin(String symbol) {
        boolean[][] visited = new boolean[size][size];

        if (symbol.equals("X")) {
            for (int row = 0; row < size; row++) {
                if (board[row][0].equals(symbol) && dfs(row, 0, symbol, visited)) {
                    return true;
                }
            }
        } else if (symbol.equals("O")) {
            for (int col = 0; col < size; col++) {
                if (board[0][col].equals(symbol) && dfs(0, col, symbol, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int row, int col, String symbol, boolean[][] visited) {
        if (row < 0 || row >= size || col < 0 || col >= size) return false;
        if (!board[row][col].equals(symbol) || visited[row][col]) return false;

        visited[row][col] = true;

        if (symbol.equals("X") && col == size - 1) return true;
        if (symbol.equals("O") && row == size - 1) return true;

        int[][] directions = {
            {-1, 0},  // sus
            {-1, 1},  // sus-dreapta
            {0, -1},  // stânga
            {0, 1},   // dreapta
            {1, -1},  // jos-stânga
            {1, 0}    // jos
        };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (dfs(newRow, newCol, symbol, visited)) {
                return true;
            }
        }

        return false;
    }
}
