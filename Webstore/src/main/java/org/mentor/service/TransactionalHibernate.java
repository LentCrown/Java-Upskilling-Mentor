package org.mentor.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Service;

@Service
public class TransactionalHibernate implements Transactional {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("h2");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    @Override
    public void begin() {
        entityManager.getTransaction().begin();
    }

    @Override
    public void commit() {
        entityManager.getTransaction().commit();
    }

    @Override
    public void rollback() {
        entityManager.getTransaction().rollback();
    }

    @Override
    public void breakConnection() {
        entityManager.close();
        entityManagerFactory.close();
    }

}
