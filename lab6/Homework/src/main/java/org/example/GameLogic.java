package org.example;

import java.util.*;

public class GameLogic {
    public int calculateScore(List<Line> lines) {
        return lines.size(); 
    }

    public int calculateBestScore(List<Dot> dots) {
        List<Line> allLines = new ArrayList<>();
        for (int i = 0; i < dots.size(); i++) {
            for (int j = i + 1; j < dots.size(); j++) {
                allLines.add(new Line(dots.get(i), dots.get(j)));
            }
        }
        return calculateMSTScore(allLines, dots);
    }
    
    public int calculateMSTScore(List<Line> lines, List<Dot> dots) {
        int score = 0;
        UnionFind uf = new UnionFind(dots.size());
        List<Line> validLines = new ArrayList<>();
    
        // Filtrează liniile care conțin puncte valide
        for (Line line : lines) {
            if (dots.contains(line.getStart()) && dots.contains(line.getEnd())) {
                validLines.add(line);
            }
        }
    
        validLines.sort(Comparator.comparingInt(Line::getLength)); 
    
        for (Line line : validLines) {
            int startIndex = dots.indexOf(line.getStart());
            int endIndex = dots.indexOf(line.getEnd());
            if (!uf.connected(startIndex, endIndex)) {
                uf.union(startIndex, endIndex);
                score += line.getLength(); 
            }
        }
    
        return score;
    }

    private static class UnionFind {
        private final int[] parent;

        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                return true;
            }
            return false;
        }

        private int count() {
            int components = 0;
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == i) {
                    components++;
                }
            }
            return components;
        }
    }
}