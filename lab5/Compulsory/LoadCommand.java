import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.io.File;

public class LoadCommand implements Command {
  private RepositoryImagine repository;
  private String filePath;

  public LoadCommand(RepositoryImagine repository, String filePath) {
      this.repository = repository;
      this.filePath = filePath;
  }

  @Override
  public void execute() throws IOException {
      ObjectMapper objectMapper = new ObjectMapper();
      List<Imagine> imagini = Arrays.asList(objectMapper.readValue(new File(filePath), Imagine[].class));
      repository.setImagini(imagini);
      System.out.println("Imaginile au fost încărcate.");
  }
}
