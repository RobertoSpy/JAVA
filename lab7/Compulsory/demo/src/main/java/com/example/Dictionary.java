package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    private final Set<String> words = new HashSet<>();
    public Set<String> getWords() {
      return words;
  }

    public Dictionary(String filePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim().toUpperCase());
            }
        } catch (Exception e) {
            System.err.println("Error loading dictionary: " + e.getMessage());
        }
    }

    public boolean isValidWord(String word) {
      boolean valid = words.contains(word.toUpperCase());
      System.out.println("Checking word: " + word + " -> " + (valid ? "VALID" : "INVALID"));
      return valid;
  }
}