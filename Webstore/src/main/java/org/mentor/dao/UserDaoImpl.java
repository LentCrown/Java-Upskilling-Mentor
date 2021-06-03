package org.mentor.dao;

import org.mentor.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager entityManager;

    public UserDaoImpl(JPAEntityManager jpaEntityManager) {
        this.entityManager = jpaEntityManager.getEntityManager();
    }

    @Override
    public void create(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public User findById(Integer id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public List<User> findAdmins() {
        return entityManager.createQuery("SELECT e FROM User e, Credential a WHERE e.id = a.id and a.is_admin=TRUE").getResultList();
    }

    @Override
    public List<User> findClients() {
        return entityManager.createQuery("SELECT e FROM User e, Credential a WHERE e.id = a.id and a.is_admin=FALSE").getResultList();
    }

    @Override
    public void update(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(User user) {
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }
}
