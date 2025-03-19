import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
public class Runway {
    private int runwayId;
    private List<LocalTime[]> schedule;

    public Runway(int runwayId) {
        this.runwayId = runwayId;
        this.schedule = new ArrayList<>();
    }

    public boolean isAvailable(LocalTime start, LocalTime end) {
        for (LocalTime[] scheduled : schedule) {
            LocalTime scheduledStart = scheduled[0];
            LocalTime scheduledEnd = scheduled[1];
            if (!(end.isBefore(scheduledStart) || start.isAfter(scheduledEnd))) {
                return false; 
            }
        }
        return true; 
    }

    public void scheduleFlight(LocalTime start, LocalTime end) {
        schedule.add(new LocalTime[]{start, end});
    }

    @Override
    public String toString() {
        return "Runway-" + runwayId;
    }
}

