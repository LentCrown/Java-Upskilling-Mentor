package org.mentor.dao;

import org.mentor.model.Order;
import org.mentor.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Order order) {
        if(order.getId() == null){
            entityManager.persist(order);
            return;
        }
        entityManager.merge(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return entityManager.createQuery("SELECT u FROM T_ORDER u").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findByUser(User user) {
        Query query = entityManager.createQuery("SELECT u FROM T_ORDER u WHERE u.user=:user");
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Order findById(Integer id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    @Transactional
    public void delete(Order order) {
        entityManager.remove(entityManager.contains(order) ? order : entityManager.merge(order));
    }
}
