
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
       
        Location[] locations = new Location[] {
            new Location("Base Alpha", LocationType.FRIENDLY),
            new Location("Outpost Bravo", LocationType.ENEMY),
            new Location("Neutral Zone", LocationType.NEUTRAL),
            new Location("Friendly Tower", LocationType.FRIENDLY),
            new Location("Enemy Fortress", LocationType.ENEMY),
            new Location("Safe Haven", LocationType.FRIENDLY),
            new Location("Enemy Camp", LocationType.ENEMY)
        };

       
        Set<Location> friendlyLocations = Arrays.stream(locations)
            .filter(location -> location.getType() == LocationType.FRIENDLY)
            .collect(Collectors.toCollection(TreeSet::new)); 
        System.out.println("Friendly Locations (Sorted by name):");
        friendlyLocations.forEach(System.out::println);

        
        List<Location> enemyLocations = Arrays.stream(locations)
            .filter(location -> location.getType() == LocationType.ENEMY)
            .sorted(Comparator.comparing(Location::getType).thenComparing(Location::getName))
            .collect(Collectors.toCollection(LinkedList::new)); 
        System.out.println("\nEnemy Locations (Sorted by type and name):");
        enemyLocations.forEach(System.out::println);
    }
}
