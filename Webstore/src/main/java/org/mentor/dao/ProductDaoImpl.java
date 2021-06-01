package org.mentor.dao;

import org.mentor.model.Product;
import org.mentor.jpa.JPAEntityManager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao{
    private final EntityManager entityManager;

    public ProductDaoImpl() {
        this.entityManager = JPAEntityManager.getInstance().getEntityManager();
    }

    @Override
    public List<Product> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = builder.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Product findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = builder.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        cq.select(root).where(builder.equal(root.get("id"), id));
        return entityManager.createQuery(cq).getSingleResult();
    }
}
