package com.example;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        try {
            ContinentDAO continentDAO = new ContinentDAO();
            CountryDAO countryDAO = new CountryDAO();

            
            continentDAO.createContinent("Lamorca");
            System.out.println("Continent added successfully.");

            
            int continentId = continentDAO.getContinentIdByName("Lamorca");
            System.out.println("Continent ID: " + continentId);

            
            countryDAO.createCountry("Bali", "BA", continentId);
            System.out.println("Country added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

