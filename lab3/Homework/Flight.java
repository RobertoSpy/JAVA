import java.time.LocalTime;


public class Flight {
  private LocalTime start;
  private LocalTime end;

  public Flight(LocalTime start, LocalTime end) {
      this.start = start;
      this.end = end;
  }

  public LocalTime getStart() {
      return start;
  }

  public LocalTime getEnd() {
      return end;
  }

  @Override
  public String toString() {
      return "Flight(" + start + ", " + end + ")";
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Flight flight = (Flight) o;
      return start.equals(flight.start) && end.equals(flight.end);
  }
}
