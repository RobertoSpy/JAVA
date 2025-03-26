package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
       
       
        List<Location> locations = LocationGenerator.generateLocations(10);

        //creare graph
        GraphManager graphManager = new GraphManager();
        locations.forEach(graphManager::addLocation);

        
        Random random = new Random();

       
        for (int i = 0; i < locations.size(); i++) {
            for (int j = 0; j < locations.size(); j++) {
                if (i != j) {  
                    
                    double distance = 1 + random.nextInt(20);  
                    graphManager.addDirectedConnection(locations.get(i), locations.get(j), distance);
                }
            }
        }

        
        Location startLocation = locations.get(0);
        System.out.println("Start Location: " + startLocation);

        
        Map<Location, Double> shortestPaths = graphManager.computeShortestPaths(startLocation);

        
        Map<LocationType, List<Location>> groupedLocations = locations.stream()
                .collect(Collectors.groupingBy(Location::getType));

       
        System.out.println("\nFastest Routes:");
        for (LocationType type : LocationType.values()) {
            List<Location> typeLocations = groupedLocations.getOrDefault(type, new ArrayList<>());
            typeLocations.sort(Comparator.comparing(shortestPaths::get)); 
            System.out.println(type + " Locations:");
            for (Location loc : typeLocations) {
                System.out.printf("%s -> %.2f km\n", loc.getName(), shortestPaths.get(loc));
            }
            System.out.println();
        }
    }
}
