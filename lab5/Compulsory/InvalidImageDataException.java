
// Custom exception for invalid image data (e.g., empty fields)
public class InvalidImageDataException extends Exception {
  public InvalidImageDataException(String message) {
      super(message);
  }
}
