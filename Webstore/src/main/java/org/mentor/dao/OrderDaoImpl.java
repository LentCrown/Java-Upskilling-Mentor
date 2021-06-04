package org.mentor.dao;

import org.mentor.model.User;
import org.mentor.model.Product;
import org.mentor.model.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final EntityManager entityManager;

    public OrderDaoImpl(JPAEntityManager jpaEntityManager) {
        this.entityManager = jpaEntityManager.getEntityManager();
    }

    @Override
    public void create(Order order) {
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Order> findAll() {
        return entityManager.createQuery("SELECT u FROM Order u").getResultList();
    }

    @Override
    public Order findById(Integer id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> findByClient(User user) {
        Query query = entityManager.createQuery("SELECT u FROM Order u WHERE u.user = ?1");
        query.setParameter(1,user);
        return query.getResultList();
    }

    @Override
    public List<Order> findByProduct(Product product) {
        return null;
    }

    @Override
    public void delete(Order order) {
        entityManager.getTransaction().begin();
        entityManager.remove(order);
        entityManager.getTransaction().commit();
    }
}
