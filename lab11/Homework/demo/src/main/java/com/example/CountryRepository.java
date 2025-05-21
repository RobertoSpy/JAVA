package com.example;

import jakarta.persistence.EntityManager;

public class CountryRepository extends AbstractRepository<Country, Integer> {
    public CountryRepository(EntityManager em) {
        super(em, Country.class);
    }

    public Country findByName(String name) {
        try {
            return em.createQuery("SELECT c FROM Country c WHERE LOWER(c.name) = LOWER(:name)", Country.class)
                     .setParameter("name", name)
                     .getSingleResult();
        } catch (Exception e) {
            logger.warning("Country not found: " + name);
            return null;
        }
    }

    public void create(Country country) {
    em.getTransaction().begin();
    em.persist(country);
    em.getTransaction().commit(); // <-- asta forțează scrierea în DB
}

}
