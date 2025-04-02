import java.util.Scanner;

public class Shell {
    private RepositoryImagine repository;

    public Shell(RepositoryImagine repository) {
        this.repository = repository;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");
            String commandName = tokens[0];

            try {
                switch (commandName) {
                    case "add":
                        repository.adaugaImagine(new Imagine(tokens[1], tokens[2], List.of(tokens[3]), tokens[4]));
                        System.out.println("Imagine adăugată.");
                        break;
                    case "remove":
                        repository.getImagini().removeIf(img -> img.nume().equals(tokens[1]));
                        System.out.println("Imagine eliminată.");
                        break;
                    case "save":
                        new SaveCommand(repository, tokens[1]).execute();
                        break;
                    case "load":
                        new LoadCommand(repository, tokens[1]).execute();
                        break;
                    case "report":
                        new ReportCommand(repository).execute();
                        break;
                    case "exit":
                        System.out.println("Ieșire.");
                        return;
                    default:
                        System.out.println("Comandă invalidă.");
                }
            } catch (Exception e) {
                System.out.println("Eroare: " + e.getMessage());
            }
        }
    }
}
