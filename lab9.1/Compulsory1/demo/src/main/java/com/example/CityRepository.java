package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class CityRepository
{
    private final EntityManager em;
    public CityRepository(EntityManager em)
    {
        this.em = em;
    }

    public void create(City city)
    {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(city);
            tx.commit();
        }
        catch (Exception e)
        {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    public City findById(int id)
    {
        return em.find(City.class, id);
    }

    public List<City> findAll()
    {
        return em.createQuery("select c from City c", City.class).getResultList();
    }

    public List<City> findByCountryId(int id)
    {
        return em.createQuery("SELECT  c from City c where c.countryId = :id", City.class)
                .setParameter("id", id)
                .getResultList();
    }


    public List<City> findCapitals()
    {
        return em.createQuery("SELECT c from City c where c.capital=true", City.class).getResultList();
    }

    public List<City> findByName(String name)
    {
        return em.createNamedQuery("City.findByName",City.class)
                .setParameter("name",name)
                .getResultList();
    }
}