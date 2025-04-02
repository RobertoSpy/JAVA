import java.util.List;

public class AddCommand implements Command {
    private RepositoryImagine repository;
    private Imagine imagine;

    public AddCommand(RepositoryImagine repository, Imagine imagine) {
        this.repository = repository;
        this.imagine = imagine;
    }

    @Override
    public void execute() throws InvalidImageDataException {
        repository.adaugaImagine(imagine);
        System.out.println("Imaginea a fost adăugată cu succes.");
    }
}

