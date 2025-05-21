package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @PersistenceContext
    private EntityManager em;

    @GetMapping
    public List<CountryDTO> getAllCountries() {
        CountryRepository countryRepository = new CountryRepository(em);
        List<Country> countries = em.createQuery("SELECT c FROM Country c", Country.class).getResultList();

        return countries.stream()
                .map(c -> new CountryDTO(
                        c.getId(),
                        c.getName(),
                        c.getCode(),
                        c.getContinent().getName()
                ))
                .collect(Collectors.toList());
    }
}
