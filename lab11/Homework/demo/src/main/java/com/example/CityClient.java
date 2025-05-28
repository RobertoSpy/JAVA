package com.example;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class CityClient {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8081/cities";

    public CityClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CityDTO> getAllCities() {
        CityDTO[] cities = restTemplate.getForObject(BASE_URL, CityDTO[].class);
        return Arrays.asList(cities);
    }


    public void addCity(CityDTO city) {
        restTemplate.postForEntity(BASE_URL, city, Void.class);
    }

    public boolean updateCity(int id, CityDTO updatedCity) {
        try {
            restTemplate.put(BASE_URL + "/" + id, updatedCity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteCity(int id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}
