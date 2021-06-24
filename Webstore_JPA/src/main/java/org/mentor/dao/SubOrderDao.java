package org.mentor.dao;

import org.mentor.model.Order;
import org.mentor.model.Product;
import org.mentor.model.SubOrder;

import java.util.List;

public interface SubOrderDao {
    void save(SubOrder subOrder);
    List<SubOrder> findByOrder(Order order);
    List<SubOrder> findByProduct(Product product);
    void delete(SubOrder subOrder);
}
