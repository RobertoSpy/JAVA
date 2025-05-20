package com.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            CountryDAO countryDAO = new CountryDAO();
            CityDAO cityDAO = new CityDAO();
            ContinentDAO continentDAO = new ContinentDAO();

            
            Map<String, Country> existingCountries = countryDAO.getAllCountries()
                    .stream()
                    .collect(Collectors.toMap(c -> c.getName().toLowerCase(), c -> c));

            
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("capitals.csv");
            if (inputStream == null) {
                System.err.println("File capitals.csv not found!");
                return;
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line = br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length < 5) {
                        System.err.println("Invalid line in CSV: " + line);
                        continue;
                    }

                    String cityName = parts[0].trim();
                    String countryName = parts[1].trim();
                    boolean capital = parts[2].trim().equalsIgnoreCase("true")
                                   || parts[2].trim().equalsIgnoreCase("yes")
                                   || parts[2].trim().equals("1");
                    double lat = Double.parseDouble(parts[3]);
                    double lon = Double.parseDouble(parts[4]);

                    Country country = existingCountries.get(countryName.toLowerCase());
                    if (country == null) {
                        
                        int continentId = continentDAO.getContinentIdByName("Asia");
                        String countryCode = countryName.length() >= 2 ? countryName.substring(0, 2).toUpperCase() : "XX";
                        countryDAO.createCountry(countryName, countryCode, continentId);

                        
                        country = countryDAO.findByName(countryName);
                        existingCountries.put(countryName.toLowerCase(), country);
                    }

                    
                    cityDAO.createCity(cityName, country.getId(), capital, lat, lon);
                }
            }

            
            List<City> cities = cityDAO.getAllCities();
            for (int i = 0; i < cities.size(); i++) {
                for (int j = i + 1; j < cities.size(); j++) {
                    City c1 = cities.get(i);
                    City c2 = cities.get(j);
                    double dist = DistanceCalculator.haversine(
                            c1.getLatitude(), c1.getLongitude(),
                            c2.getLatitude(), c2.getLongitude());
                    System.out.printf("Distance between %s and %s: %.2f km%n",
                            c1.getName(), c2.getName(), dist);
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
