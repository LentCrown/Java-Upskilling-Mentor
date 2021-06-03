package org.mentor.dao;

import org.mentor.model.User;
import org.mentor.model.Product;
import org.mentor.model.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final EntityManager entityManager;

    public OrderDaoImpl() {
        this.entityManager = JPAEntityManager.getInstance().getEntityManager();
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
        return null;
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
