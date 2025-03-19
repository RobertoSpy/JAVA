import java.util.HashMap;
import java.util.Map;
import java.util.Set;
 public class Booking {
    private Airport airport;
    private Set<Flight> flights;

    public Booking(Airport airport, Set<Flight> flights) {
        this.airport = airport;
        this.flights = flights;
    }
//mapez si asignez zborul pe pista daca e valabil
    public Map<Flight, Runway> solve() {
        Map<Flight, Runway> flightRunwayMap = new HashMap<>();
        for (Flight flight : flights) {
            Runway assignedRunway = airport.getAvailableRunway(flight.getStart(), flight.getEnd());
            if (assignedRunway != null) {
                assignedRunway.scheduleFlight(flight.getStart(), flight.getEnd());
                flightRunwayMap.put(flight, assignedRunway);
            } else {
                flightRunwayMap.put(flight, null); 
            }
        }
        return flightRunwayMap;
    }
}
