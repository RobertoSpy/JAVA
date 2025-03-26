import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryImagine {
    private List<Imagine> imagini;  

    public RepositoryImagine() {
        this.imagini = new ArrayList<>();  
    }

    public void adaugaImagine(Imagine imagine) throws InvalidImageDataException {
        if (imagine.nume() == null || imagine.nume().isEmpty() ||  
            imagine.data() == null || imagine.data().isEmpty() ||  
            imagine.locatie() == null || imagine.locatie().isEmpty()) {  
            throw new InvalidImageDataException("Datele imaginii sunt invalide.");
        }
        imagini.add(imagine);
    }

    public Imagine obtineImagine(String nume) throws ImageNotFoundException {
        for (Imagine imagine : imagini) {
            if (imagine.nume().equals(nume)) {  
                return imagine;
            }
        }
        throw new ImageNotFoundException("Imaginea cu numele " + nume + " nu a fost găsită.");
    }

    public void afiseazaImagini() {
        for (Imagine imagine : imagini) {
            System.out.println(imagine);
        }
    }
}
