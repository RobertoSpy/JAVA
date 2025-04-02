public class RemoveCommand implements Command {
  private RepositoryImagine repository;
  private String numeImagine;

  public RemoveCommand(RepositoryImagine repository, String numeImagine) {
      this.repository = repository;
      this.numeImagine = numeImagine;
  }

  @Override
  public void execute() throws ImageNotFoundException {
      Imagine imagine = repository.obtineImagine(numeImagine);
      repository.getImagini().remove(imagine);
      System.out.println("Imaginea a fost ștearsă cu succes.");
  }
}
