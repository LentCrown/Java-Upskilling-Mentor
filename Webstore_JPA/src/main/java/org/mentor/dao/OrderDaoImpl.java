package org.mentor.dao;

import org.mentor.model.User;
import org.mentor.model.Product;
import org.mentor.model.Order;
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
    public List<Order> findAll() {
        return entityManager.createQuery("SELECT u FROM T_ORDER u").getResultList();
    }

    @Override
    public Order findById(Integer id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> findByClient(User user) {
        Query query = entityManager.createQuery("SELECT u FROM T_ORDER u WHERE u.user = :user");
        query.setParameter("user",user);
        return query.getResultList();
    }

    @Override
    public List<Order> findByProduct(Product product) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Order order) {
        entityManager.remove(order);
    }
}
