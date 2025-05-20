package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ContinentRepository
{ private final EntityManager em;
  public ContinentRepository(EntityManager em)
  {
      this.em = em;
  }

  public void create(Continent continent)
  {
      EntityTransaction tx = em.getTransaction();
      try
      {
          tx.begin();
          em.persist(continent); 
          tx.commit(); 
      }
      catch (Exception e)
      {
          if(tx.isActive()) tx.rollback();
          e.printStackTrace();
      }
  }

  public Continent findById(Integer id)
  {
      return em.find(Continent.class, id);
  }

  public Continent findByName(String name) {
      try {
          return em.createQuery("SELECT c FROM Continent c WHERE c.name = :name", Continent.class)
                  .setParameter("name", name)
                  .getSingleResult();
      } catch (Exception e)
      {
          return null;
      }
  }


  public List<Continent> findAll()
  {
      return em.createNamedQuery("Continent.findAll", Continent.class).getResultList();
  }
}