package org.mentor.dao;

import org.mentor.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
            return;
        }
        entityManager.merge(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Integer id) {
        return entityManager.find(User.class,id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM T_USER u").getResultList();
    }

    @Override
    @Transactional
    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }
}
