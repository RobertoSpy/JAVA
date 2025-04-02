import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class SaveCommand implements Command {
    private RepositoryImagine repository;
    private String filePath;

    public SaveCommand(RepositoryImagine repository, String filePath) {
        this.repository = repository;
        this.filePath = filePath;
    }

    @Override
    public void execute() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath), repository.getImagini());
        System.out.println("Imaginile au fost salvate în fișier.");
    }
}
