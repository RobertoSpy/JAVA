class Airliner extends Aircraft implements PassengerCapable {
  private int passengerCapacity;

  public Airliner(String model, String tailNumber, String callSign, int passengerCapacity) {
      super(model, tailNumber, callSign);
      this.passengerCapacity = passengerCapacity;
  }

  @Override
  public int getPassengerCapacity() {
      return passengerCapacity;
  }

  @Override
  public String toString() {
      return super.toString() + ", Passenger Capacity: " + passengerCapacity;
  }
}

