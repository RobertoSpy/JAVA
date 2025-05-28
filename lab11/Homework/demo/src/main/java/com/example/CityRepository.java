package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    // Metode personalizate - Spring Data le implementează automat:

    List<City> findByCountryId(int countryId);

    List<City> findByCapitalTrue();

    List<City> findByName(String name);
}
