package com.example;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceManager
{ private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamplePU");
  private PersistenceManager() {}
  static EntityManagerFactory getEntityManagerFactory()
  {
      return emf;
  }

  public static void close()
  {
      if(emf.isOpen()) emf.close();
  }
}