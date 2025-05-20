package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    private final Set<String> words = new HashSet<>();

    public Dictionary(String filePath) {
        try {
            Files.lines(Paths.get(filePath)).forEach(word -> words.add(word.trim().toUpperCase()));
        } catch (IOException e) {
            System.err.println("Error loading dictionary: " + e.getMessage());
        }
    }

    public boolean isValidWord(String word) {
        return words.contains(word.toUpperCase());
    }
}