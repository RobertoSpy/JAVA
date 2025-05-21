package com.example;

import jakarta.persistence.EntityManager;
import java.util.List;

public class CityRepository extends AbstractRepository<City, Integer> {
    public CityRepository(EntityManager em) {
        super(em, City.class);
    }

    public List<City> findByCountryId(int id) {
        return em.createQuery("SELECT c FROM City c WHERE c.country.id = :id", City.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<City> findCapitals() {
        return em.createQuery("SELECT c FROM City c WHERE c.capital = true", City.class)
                .getResultList();
    }

    public List<City> findByName(String name) {
        return em.createNamedQuery("City.findByName", City.class)
                .setParameter("name", name)
                .getResultList();
    }

    public void create(City city) {
    em.getTransaction().begin();
    em.persist(city);
    em.getTransaction().commit(); // <-- asta forțează scrierea în DB
}

}
