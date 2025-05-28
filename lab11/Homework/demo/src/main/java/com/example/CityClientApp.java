package com.example;

import java.util.List;
import java.util.Scanner;

import org.springframework.web.client.RestTemplate;

public class CityClientApp implements Runnable {

    private final CityClient cityClient = new CityClient(new RestTemplate());

    public static void main(String[] args) {
        new CityClientApp().run();
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Meniu ===");
            System.out.println("1. Afiseaza toate orasele");
            System.out.println("3. Adauga un oras nou");
            System.out.println("4. Sterge un oras dupa ID");
            System.out.println("5. Modifica un oras dupa ID");
            System.out.println("6. Iesire");
            System.out.print("Alege o optiune: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opțiune invalidă, încearcă din nou.");
                continue;
            }

            switch (choice) {
                case 1:
                    afiseazaToateOrasele();
                    break;
                case 3:
                    adaugaOras(scanner);
                    break;
                case 4:
                    stergeOras(scanner);
                    break;
                case 5:
                    modificaOras(scanner);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Optiune invalida!");
            }
        }

        scanner.close();
        System.out.println("Aplicatia s-a încheiat.");
    }

    private void afiseazaToateOrasele() {
        List<CityDTO> cities = cityClient.getAllCities();
        if (cities.isEmpty()) {
            System.out.println("Nu exista orase în baza de date.");
        } else {
            cities.forEach(System.out::println);
        }
    }

    

    private void adaugaOras(Scanner scanner) {
        System.out.print("Nume oras: ");
        String name = scanner.nextLine();

        System.out.print("Country ID: ");
        int countryId = Integer.parseInt(scanner.nextLine());

        System.out.print("Country Name: ");
        String countryName = scanner.nextLine();

        System.out.print("Este capitala? (true/false): ");
        boolean capital = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Latitudine: ");
        double latitude = Double.parseDouble(scanner.nextLine());

        System.out.print("Longitudine: ");
        double longitude = Double.parseDouble(scanner.nextLine());

        CityDTO city = new CityDTO(null, name, countryId, countryName, capital, latitude, longitude);
        cityClient.addCity(city);

        System.out.println("Oras adaugat!");
    }

    private void stergeOras(Scanner scanner) {
        System.out.print("ID oraa de sters: ");
        int id = Integer.parseInt(scanner.nextLine());

        cityClient.deleteCity(id);
        System.out.println("Oras sters!");
    }

    private void modificaOras(Scanner scanner) {
        System.out.print("ID oraș de modificat: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nume nou: ");
        String name = scanner.nextLine();

        System.out.print("Country ID nou: ");
        int countryId = Integer.parseInt(scanner.nextLine());

        System.out.print("Country Name nou: ");
        String countryName = scanner.nextLine();

        System.out.print("Este capitala? (true/false): ");
        boolean capital = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Latitudine noua: ");
        double latitude = Double.parseDouble(scanner.nextLine());

        System.out.print("Longitudine noua: ");
        double longitude = Double.parseDouble(scanner.nextLine());

        CityDTO city = new CityDTO(id, name, countryId, countryName, capital, latitude, longitude);

        boolean success = cityClient.updateCity(id, city);
        if (success) {
            System.out.println("Oras actualizat!");
        } else {
            System.out.println("Eroare la actualizarea orașului (poate nu exista ID-ul).");
        }
    }
}
