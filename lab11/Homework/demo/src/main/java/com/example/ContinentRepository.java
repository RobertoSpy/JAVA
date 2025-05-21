package com.example;

import jakarta.persistence.EntityManager;
import java.util.List;

public class ContinentRepository extends AbstractRepository<Continent, Integer> {
    public ContinentRepository(EntityManager em) {
        super(em, Continent.class);
    }

    public Continent findByName(String name) {
        try {
            return em.createQuery("SELECT c FROM Continent c WHERE c.name = :name", Continent.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Continent> findAllOrdered() {
        return em.createNamedQuery("Continent.findAll", Continent.class).getResultList();
    }

    public void create(Continent continent) {
    em.getTransaction().begin();
    em.persist(continent);
    em.getTransaction().commit(); 
}

}
