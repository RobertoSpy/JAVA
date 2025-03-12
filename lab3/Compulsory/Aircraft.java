abstract class Aircraft implements Comparable<Aircraft> {
  protected String model;
  protected String tailNumber;
  protected String callSign;

  public Aircraft(String model, String tailNumber, String callSign) {
      this.model = model;
      this.tailNumber = tailNumber;
      this.callSign = callSign;
  }

  public String getModel() {
      return model;
  }

  @Override
  public int compareTo(Aircraft other) {
      return this.model.compareTo(other.model);
  }

  @Override
  public String toString() {
      return "Model: " + model + ", Tail Number: " + tailNumber + ", Call Sign: " + callSign;
  }
}