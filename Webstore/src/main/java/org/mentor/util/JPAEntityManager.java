package org.mentor.util;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Repository
public class JPAEntityManager {
    private static JPAEntityManager instance;

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private JPAEntityManager() {
        entityManagerFactory= Persistence.createEntityManagerFactory("webstore.h2");
        entityManager= entityManagerFactory.createEntityManager();
    }

    public static JPAEntityManager getInstance(){
        if (instance == null){
            instance = new JPAEntityManager();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
