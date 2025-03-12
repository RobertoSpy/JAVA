class Freighter extends Aircraft implements CargoCapable {
  private double maxPayload;

  public Freighter(String model, String tailNumber, String callSign, double maxPayload) {
      super(model, tailNumber, callSign);
      this.maxPayload = maxPayload;
  }

  @Override
  public double getMaxPayload() {
      return maxPayload;
  }

  @Override
  public String toString() {
      return super.toString() + ", Max Payload: " + maxPayload + " kg";
  }
}