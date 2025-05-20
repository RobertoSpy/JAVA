package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
  private final Connection connection;

  public CityDAO() throws SQLException {
      this.connection = DatabaseConnectionManager.getConnection();
  }

  public void createCity(String name, int countryId, boolean capital, double latitude, double longitude) throws SQLException {
    String query = "INSERT INTO cities (id, name, country_id, capital, latitude, longitude) VALUES (cities_seq.NEXTVAL, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, name);
        stmt.setInt(2, countryId);
        stmt.setInt(3, capital ? 1 : 0); 
        stmt.setDouble(4, latitude);
        stmt.setDouble(5, longitude);
        stmt.executeUpdate();
    }
}

  public List<City> getAllCities() throws SQLException {
      String query = "SELECT * FROM cities";
      List<City> cities = new ArrayList<>();
      try (Statement stmt = connection.createStatement();
           ResultSet rs = stmt.executeQuery(query)) {
          while (rs.next()) {
              cities.add(new City(
                      rs.getInt("id"),
                      rs.getInt("country_id"),
                      rs.getString("name"),
                      rs.getInt("capital") == 1,
                      rs.getDouble("latitude"),
                      rs.getDouble("longitude")
              ));
          }
      }
      return cities;
  }
}
