package org.mentor.dao;

import org.mentor.repository.Client;
import org.mentor.repository.Order;
import org.mentor.util.JPAEntityManager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao{
    private final EntityManager entityManager;

    public OrderDaoImpl() {
        this.entityManager = JPAEntityManager.getInstance().getEntityManager();
    }

    @Override
    public List<Order> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> cq = builder.createQuery(Order.class);
        Root<Order> root = cq.from(Order.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Order findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> cq = builder.createQuery(Order.class);
        Root<Order> root = cq.from(Order.class);
        cq.select(root).where(builder.equal(root.get("id"), id));
        return entityManager.createQuery(cq).getSingleResult();
    }
}
