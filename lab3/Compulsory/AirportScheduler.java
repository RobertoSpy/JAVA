import java.util.Arrays;

public class AirportScheduler {
    public static void main(String[] args) {
       
        Aircraft[] aircraftArray = {
            new Airliner("Boeing 747", "N12345", "Flight A1", 400),
            new Freighter("Cargo King 2000", "F67890", "Flight C1", 20000),
            new Drone("X-Drone", "D55555", "Flight D1", 50, 120),
            new Freighter("AirHauler 500", "F88888", "Flight C2", 30000)
        };

        
        Arrays.sort(aircraftArray);

        
        System.out.println("All Aircraft:");
        for (Aircraft a : aircraftArray) {
            System.out.println(a);
        }

        
        CargoCapable[] cargoAircraft = new CargoCapable[aircraftArray.length];
        int count = 0;
        for (Aircraft a : aircraftArray) {
            if (a instanceof CargoCapable) {
                cargoAircraft[count++] = (CargoCapable) a;
            }
        }

        
        cargoAircraft = Arrays.copyOf(cargoAircraft, count);

       
        System.out.println("\nAircraft that can transport goods:");
        for (CargoCapable c : cargoAircraft) {
            System.out.println(c);
        }
    }
}
