package com.example;

import java.util.HashMap;
import java.util.Map;

public class RouteSafetyData {
    private final Map<LocationType, Long> typeCount;

    public RouteSafetyData() {
        this.typeCount = new HashMap<>();
    }

    //adaugam typeLocation
    public void addLocationType(LocationType type) {
        typeCount.put(type, typeCount.getOrDefault(type, 0L) + 1);
    }

    
    public Map<LocationType, Long> getTypeCount() {
        return typeCount;
    }
}
