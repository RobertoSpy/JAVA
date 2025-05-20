package com.example;

import jakarta.persistence.NoResultException;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MainJPA
{
    public static void main(String[] args)
    {

        testJPA();
        PersistenceManager.close();

    }

    public static void testJPA() {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String newContinentName = "Australia";

        
        Continent existing = null;
        try {
            existing = em.createQuery(
                            "SELECT c FROM Continent c WHERE c.name = :name", Continent.class)
                    .setParameter("name", newContinentName)
                    .getSingleResult();
            System.out.println("Continentul '" + newContinentName + "' exista deja: " + existing);
        } catch (NoResultException e) {
            
            Continent newContinent = new Continent(newContinentName);
            em.persist(newContinent);
            System.out.println("Continentul '" + newContinentName + "' a fost adaugat.");
        }

        em.getTransaction().commit();

       
        List<Continent> allContinents = em.createNamedQuery("Continent.findAll", Continent.class).getResultList();
        System.out.println("Toate continentele din baza de date:");
        allContinents.forEach(System.out::println);

        em.close();
    }
}