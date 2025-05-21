package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractRepository<T, ID> {
    protected final EntityManager em;
    protected final Class<T> entityClass;
    protected final Logger logger = Logger.getLogger(getClass().getName());

    public AbstractRepository(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        EntityTransaction tx = em.getTransaction();
        try {
            long start = System.nanoTime();
            tx.begin();
            em.persist(entity);
            tx.commit();
            long end = System.nanoTime();
            logger.info("Saved entity in " + (end - start) / 1_000_000 + " ms");
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            logger.severe("Error creating entity: " + e.getMessage());
            
        }
    }

    public T findById(ID id) {
        return em.find(entityClass, id);
    }

    public List<T> findAll() {
        return em.createQuery("select e from " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }
}
