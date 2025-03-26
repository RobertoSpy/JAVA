package com.example;

import com.github.javafaker.Faker;
import java.util.*;

public class LocationGenerator {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    public static Location generateRandomLocation() {
        String name = faker.address().cityName(); 
        LocationType type = LocationType.values()[random.nextInt(LocationType.values().length)];
        return new Location(name, type);
    }

    public static List<Location> generateLocations(int count) {
        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            locations.add(generateRandomLocation());
        }
        return locations;
    }
}

