import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
       
        String path = "C:\\Users\\Roberto\\OneDrive\\Desktop\\JAVA\\lab5\\Compulsory\\imagines\\tigru1.jpg";

       
        Imagine imagine1 = new Imagine("Vacanta", "2025-03-25", Arrays.asList("plaja", "vacanta", "vara"), path);

       
        RepositoryImagine repository = new RepositoryImagine();

        try {
            
            repository.adaugaImagine(imagine1);

           
            Imagine imagineGasita = repository.obtineImagine("Vacanta");

            
            System.out.println(imagineGasita);

           //deschidere file cu locatia imaginii
            File file = new File(imagineGasita.locatie());  
            if (file.exists()) {
                Desktop.getDesktop().open(file);  
            } else {
                System.out.println("Imaginea nu a fost găsită!");
            }

        } catch (InvalidImageDataException | ImageNotFoundException e) {
            System.out.println("Eroare: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Eroare la deschiderea imaginii: " + e.getMessage());
        }
    }
}
