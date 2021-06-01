package org.mentor.dao;

import org.mentor.repository.Client;
import org.mentor.repository.Product;
import org.mentor.repository.Receipt;
import org.mentor.util.JPAEntityManager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ReceiptDaoImpl implements ReceiptDao {
    private final EntityManager entityManager;

    public ReceiptDaoImpl() {
        this.entityManager = JPAEntityManager.getInstance().getEntityManager();
    }

    @Override
    public List<Receipt> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Receipt> cq = builder.createQuery(Receipt.class);
        Root<Receipt> root = cq.from(Receipt.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Receipt findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Receipt> cq = builder.createQuery(Receipt.class);
        Root<Receipt> root = cq.from(Receipt.class);
        cq.select(root).where(builder.equal(root.get("id"), id));
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public List<Receipt> findByClient(Client client) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Receipt> cq = builder.createQuery(Receipt.class);
        Root<Receipt> root = cq.from(Receipt.class);
        cq.select(root).where(builder.equal(root.get("client"), client));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Receipt> findByProduct(Product product) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Receipt> cq = builder.createQuery(Receipt.class);
        Root<Receipt> root = cq.from(Receipt.class);
        cq.select(root).where(builder.equal(root.get("product"), product));
        return entityManager.createQuery(cq).getResultList();
    }
}
