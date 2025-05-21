package com.example;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;



public class JPQLExecutor {
    private static final Logger logger = JPQLLogger.getLogger();

    public List<?> executeQuery(EntityManager em, String jpqlQuery) {
        long startTime = System.nanoTime(); 

        try {
            TypedQuery<?> query = em.createQuery(jpqlQuery, Object.class);
            List<?> result = query.getResultList();
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            
           
            logger.info("Executed JPQL query in " + duration + " nanoseconds");
            return result;
        } catch (Exception e) {
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            
            
            logger.log(Level.SEVERE, "Error executing JPQL query in " + (duration) + " nanoseconds", e);
            return null;
        }
    }
}
