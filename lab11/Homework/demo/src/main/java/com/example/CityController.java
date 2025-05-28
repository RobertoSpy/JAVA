package com.example;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cities")
@Tag(name = "City API", description = "API pentru gestionarea orașelor")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping
    @Operation(summary = "Obtine lista tuturor oraselor")
    public List<CityDTO> getAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @Operation(summary = "Adauga un oraș nou")
    public ResponseEntity<String> addCity(@RequestBody CityDTO cityDTO) {
        Optional<Country> countryOpt = countryRepository.findById(cityDTO.getCountryId());

        if (countryOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid countryId");
        }

        City city = new City(
                cityDTO.getName(),
                countryOpt.get(),
                cityDTO.isCapital(),
                cityDTO.getLatitude(),
                cityDTO.getLongitude()
        );

        cityRepository.save(city);
        return ResponseEntity.ok("City added with ID: " + city.getId());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizeaza un oraș existent după ID")
    public ResponseEntity<String> updateCity(@PathVariable("id") Integer id, @RequestBody CityDTO cityDTO) {
        Optional<City> cityOpt = cityRepository.findById(id);
        if (cityOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City with ID " + id + " not found");
        }

        City city = cityOpt.get();
        city.setName(cityDTO.getName());
        city.setCapital(cityDTO.isCapital());
        city.setLatitude(cityDTO.getLatitude());
        city.setLongitude(cityDTO.getLongitude());

        Optional<Country> countryOpt = countryRepository.findById(cityDTO.getCountryId());
        if (countryOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Country with ID " + cityDTO.getCountryId() + " not found");
        }
        city.setCountry(countryOpt.get());

        try {
            cityRepository.save(city);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving city: " + e.getMessage());
        }

        return ResponseEntity.ok("City updated");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Șterge un oraș după ID")
    public ResponseEntity<String> deleteCity(@PathVariable("id") Integer id) {
        Optional<City> cityOpt = cityRepository.findById(id);
        if (cityOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        cityRepository.delete(cityOpt.get());
        return ResponseEntity.ok("City deleted");
    }

    private CityDTO convertToDTO(City city) {
        Integer countryId = null;
        if (city.getCountry() != null) {
            countryId = city.getCountry().getId();
        }

        return new CityDTO(
                city.getId(),
                city.getName(),
                countryId,
                (city.getCountry() != null ? city.getCountry().getName() : null),
                city.isCapital(),
                city.getLatitude(),
                city.getLongitude()
        );
    }
}
