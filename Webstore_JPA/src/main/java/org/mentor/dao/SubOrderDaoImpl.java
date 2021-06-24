package org.mentor.dao;

import org.mentor.model.Order;
import org.mentor.model.Product;
import org.mentor.model.SubOrder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SubOrderDaoImpl implements SubOrderDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(SubOrder subOrder) {
        Query query = entityManager.createNativeQuery("INSERT INTO T_SUBORDERS(PRODUCT_ID, ORDER_ID, BOUGHT, SUB_PRICE) values (?,?,?,?)");
        query.setParameter(1, subOrder.getProduct_id());
        query.setParameter(2, subOrder.getOrder_id());
        query.setParameter(3, subOrder.getBought());
        query.setParameter(4, subOrder.getSub_price());
        query.executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubOrder> findByOrder(Order order) {
        Query query = entityManager.createQuery("SELECT u FROM T_SUBORDERS u WHERE u.order_id=:order_id");
        query.setParameter("order_id", order.getId());
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubOrder> findByProduct(Product product) {
        Query query = entityManager.createQuery("SELECT u FROM T_SUBORDERS u WHERE u.product_id=:product_id");
        query.setParameter("product_id", product.getId());
        return query.getResultList();
    }

    @Override
    @Transactional
    public void delete(SubOrder subOrder) {
        entityManager.remove(entityManager.contains(subOrder) ? subOrder : entityManager.merge(subOrder));
    }
}
