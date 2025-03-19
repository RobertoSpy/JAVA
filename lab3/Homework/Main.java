import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        
        Airport airport = new Airport(3); 

        
        Set<Flight> flights = new HashSet<>();
        flights.add(new Flight(LocalTime.of(10, 0), LocalTime.of(10, 30)));
        flights.add(new Flight(LocalTime.of(10, 15), LocalTime.of(10, 45)));
        flights.add(new Flight(LocalTime.of(11, 0), LocalTime.of(11, 30)));
        flights.add(new Flight(LocalTime.of(11, 10), LocalTime.of(11, 40)));

        
        Booking booking = new Booking(airport, flights);

       
        Map<Flight, Runway> result = booking.solve();

       
        for (Map.Entry<Flight, Runway> entry : result.entrySet()) {
            if (entry.getValue() != null) {
                System.out.println("Zborul " + entry.getKey() + " a fost programat pe " + entry.getValue());
            } else {
                System.out.println("Zborul " + entry.getKey() + " nu a putut fi programat pe nicio pistă.");
            }
        }
    }
}
