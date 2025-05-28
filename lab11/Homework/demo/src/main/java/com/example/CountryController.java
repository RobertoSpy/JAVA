package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping
    public List<CountryDTO> getAllCountries() {
        List<Country> countries = countryRepository.findAll();

        return countries.stream()
                .map(c -> new CountryDTO(
                        c.getId(),
                        c.getName(),
                        c.getCode(),
                        c.getContinent() != null ? c.getContinent().getName() : null
                ))
                .collect(Collectors.toList());
    }
}
