package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int numLocations = 10; 
        List<Location> locations = LocationGenerator.generateLocations(numLocations);

        
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

        
        FloydWarshall floydWarshall = new FloydWarshall(graphManager);
        Map<Location, Map<Location, Double>> allShortestPaths = floydWarshall.computeAllPairShortestPaths();

        
        Map<LocationType, List<Location>> groupedLocations = locations.stream()
                .collect(Collectors.groupingBy(Location::getType));

        
        System.out.println("Fastest Routes:");
        for (LocationType type : LocationType.values()) {
            List<Location> typeLocations = groupedLocations.getOrDefault(type, new ArrayList<>());
            typeLocations.sort(Comparator.comparing(loc -> allShortestPaths.get(locations.get(0)).get(loc)));
            System.out.println(type + " Locations:");
            for (Location loc : typeLocations) {
                System.out.printf("%s -> %.2f km\n", loc.getName(), allShortestPaths.get(locations.get(0)).get(loc));
            }
            System.out.println();
        }

        
        Map<Location, Map<Location, RouteSafetyData>> safetyMap = new HashMap<>();
        for (Location source : locations) {
            safetyMap.put(source, new HashMap<>());
            for (Location destination : locations) {
                if (!source.equals(destination)) {
                    RouteSafetyData safetyData = new RouteSafetyData();
                    locations.forEach(loc -> safetyData.addLocationType(loc.getType()));
                    safetyMap.get(source).put(destination, safetyData);
                }
            }
        }

        
        System.out.println("Route Safety Data:");
        for (var entry : safetyMap.entrySet()) {
            Location source = entry.getKey();
            for (var destEntry : entry.getValue().entrySet()) {
                Location destination = destEntry.getKey();
                RouteSafetyData safetyData = destEntry.getValue();
                System.out.println("From " + source.getName() + " to " + destination.getName() + ": " + safetyData.getTypeCount());
            }
        }

        
        calculateStatistics(allShortestPaths);
    }

    private static void calculateStatistics(Map<Location, Map<Location, Double>> shortestPaths) {
        double totalDistance = 0;
        int count = 0;
        double minDistance = Double.MAX_VALUE;
        double maxDistance = Double.MIN_VALUE;
        String shortestPath = "", longestPath = "";

        for (var entry : shortestPaths.entrySet()) {
            Location source = entry.getKey();
            for (var destEntry : entry.getValue().entrySet()) {
                Location destination = destEntry.getKey();
                double distance = destEntry.getValue();
                totalDistance += distance;
                count++;
                
                if (distance < minDistance) {
                    minDistance = distance;
                    shortestPath = source.getName() + " -> " + destination.getName();
                }
                if (distance > maxDistance) {
                    maxDistance = distance;
                    longestPath = source.getName() + " -> " + destination.getName();
                }
            }
        }

        double avgDistance = totalDistance / count;
        System.out.printf("Average distance: %.2f km\n", avgDistance);
        System.out.println("Shortest path: " + shortestPath + " = " + minDistance + " km");
        System.out.println("Longest path: " + longestPath + " = " + maxDistance + " km");
    }
}
