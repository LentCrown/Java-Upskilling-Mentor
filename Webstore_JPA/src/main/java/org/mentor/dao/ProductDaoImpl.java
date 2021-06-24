package org.mentor.dao;

import org.mentor.model.Product;
import org.mentor.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Product product) {
        if(product.getId() == null){
            entityManager.persist(product);
            return;
        }
        entityManager.merge(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Integer id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return entityManager.createQuery("SELECT e FROM T_PRODUCT e").getResultList();
    }

    @Override
    @Transactional
    public void delete(Product product) {
        entityManager.remove(entityManager.contains(product) ? product : entityManager.merge(product));
    }
}
