package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContinentDAO {

    private Connection connection;

    public ContinentDAO() throws SQLException {
        this.connection = DatabaseConnectionManager.getConnection();

    }

    public void createContinent(String name) throws SQLException {
        String query = "INSERT INTO continents (name) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }

    public int getContinentIdByName(String name) throws SQLException {
        String query = "SELECT id FROM continents WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        throw new SQLException("Continent " + name + " not found.");
    }

    public Continent findById(int id) throws SQLException {
        String query = "SELECT * FROM continents WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Continent(rs.getInt("id"), rs.getString("name"));
            }
            return null;
        }
    }

    public Continent findByName(String name) throws SQLException {
        String query = "SELECT * FROM continents WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Continent(rs.getInt("id"), rs.getString("name"));
            }
            return null;
        }
    }

    public List<Continent> getAllContinents() throws SQLException {
        String query = "SELECT * FROM continents";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            List<Continent> continents = new ArrayList<>();
            while (rs.next()) {
                continents.add(new Continent(rs.getInt("id"), rs.getString("name")));
            }
            return continents;
        }
    }
}
