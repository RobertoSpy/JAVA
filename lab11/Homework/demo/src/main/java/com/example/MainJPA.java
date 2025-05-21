package com.example;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

public class MainJPA {
    public static void main(String[] args) {
        JPQLLogger.setupLogger(); 
        Logger logger = JPQLLogger.getLogger(); 

        logger.info("Aplicația a pornit");
        EntityManager em = null;

        try {
            em = PersistenceManager.getEntityManagerFactory().createEntityManager();
            
           
            JPQLExecutor executor = new JPQLExecutor(); 

            
            ContinentRepository continentRepo = new ContinentRepository(em);
            CountryRepository countryRepo = new CountryRepository(em);
            CityRepository cityRepo = new CityRepository(em);

            em.getTransaction().begin();

           
            String newContinentName = "Australia";
            Continent existing = continentRepo.findByName(newContinentName);

            if (existing == null) {
                Continent newContinent = new Continent(newContinentName);
                continentRepo.create(newContinent);
                logger.info("Continentul '" + newContinentName + "' a fost adăugat.");
            } else {
                logger.info("Continentul '" + newContinentName + "' există deja: " + existing);
            }

            em.flush();

           
            Continent australia = continentRepo.findByName("Australia");

            
            if (australia != null) {
                Country testland = countryRepo.findByName("Anaconda");

                if (testland == null) {
                    testland = new Country();
                    testland.setName("Anaconda");
                    testland.setCode("AN");
                    testland.setContinent(australia);
                    countryRepo.create(testland);
                    logger.info("Țara 'Anaconda' a fost adăugată.");
                } else {
                    logger.info("Țara 'Anaconda' există deja: " + testland.getId());
                }

                em.flush();

               
                List<City> results = cityRepo.findByName("Piton");
                City existingCity = results.isEmpty() ? null : results.get(0);

                if (existingCity == null) {
                    City city = new City("Piton", testland, true);
                    cityRepo.create(city);
                    logger.info("Orașul 'Piton' a fost adăugat ca și capitală în Testland.");
                } else {
                    logger.info("Orașul 'Piton' există deja: " + existingCity.getId());
                }
            }

            em.getTransaction().commit();

            
            System.out.println("\nToate continentele (via JPQLExecutor):");
            List<Continent> allContinents = (List<Continent>) executor.executeQuery(em, "SELECT c FROM Continent c");
            if (allContinents != null) {
                allContinents.forEach(System.out::println);
            }

            System.out.println("\nToate orașele (via JPQLExecutor):");
            List<City> cities = (List<City>) executor.executeQuery(em, "SELECT c FROM City c");
            if (cities != null) {
                cities.forEach(System.out::println);
            }

        } catch (Exception e) {
            JPQLLogger.logError("Eroare în aplicație:", e);
        } finally {
            if (em != null) {
                em.close();
            }
            PersistenceManager.close();
            logger.info("Aplicația s-a încheiat.");
        }
    }
}
