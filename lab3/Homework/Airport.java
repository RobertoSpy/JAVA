import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

 public class Airport {
    private List<Runway> runways;

    public Airport(int numRunways) {
        this.runways = new ArrayList<>();
        for (int i = 0; i < numRunways; i++) {
            runways.add(new Runway(i));
        }
    }
//vad daca este valabila pista la ora aia
    public Runway getAvailableRunway(LocalTime start, LocalTime end) {
        for (Runway runway : runways) {
            if (runway.isAvailable(start, end)) {
                return runway; 
            }
        }
        return null; 
    }

    public List<Runway> getRunways() {
        return runways;
    }
}

  

