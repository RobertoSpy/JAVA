package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {

    private Connection connection;

    public CountryDAO() throws SQLException {
        this.connection = DatabaseConnectionManager.getInstance().getConnection();
    }

    public void createCountry(String name, String code, int continentId) throws SQLException {
        String query = "INSERT INTO countries (name, code, continent_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, code);
            stmt.setInt(3, continentId);
            stmt.executeUpdate();
        }
    }

    public Country findById(int id) throws SQLException {
        String query = "SELECT * FROM countries WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Country(rs.getInt("id"), rs.getString("name"), rs.getString("code"), rs.getInt("continent_id"));
            }
            return null;
        }
    }

    public List<Country> getAllCountries() throws SQLException {
        String query = "SELECT * FROM countries";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            List<Country> countries = new ArrayList<>();
            while (rs.next()) {
                countries.add(new Country(rs.getInt("id"), rs.getString("name"), rs.getString("code"), rs.getInt("continent_id")));
            }
            return countries;
        }
    }
}
