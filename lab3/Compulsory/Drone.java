public class Drone extends Aircraft implements CargoCapable {
  private double maxPayload;
  private double batteryLife;

  public Drone(String model, String tailNumber, String callSign, double maxPayload, double batteryLife) {
      super(model, tailNumber, callSign);
      this.maxPayload = maxPayload;
      this.batteryLife = batteryLife;
  }

  @Override
  public double getMaxPayload() {
      return maxPayload;
  }

  public double getBatteryLife() {
      return batteryLife;
  }

  @Override
  public String toString() {
      return super.toString() + ", Max Payload: " + maxPayload + " kg, Battery Life: " + batteryLife + " min";
  }
} 
