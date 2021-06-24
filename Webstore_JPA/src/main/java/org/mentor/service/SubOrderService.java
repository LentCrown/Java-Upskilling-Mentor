package org.mentor.service;

import org.mentor.domain.Pack;
import org.mentor.model.Order;
import org.mentor.model.Product;
import org.mentor.model.SubOrder;

import java.util.List;

public interface SubOrderService {
    void save(List<Pack> packList, Order order);
    List<SubOrder> getByOrder(Order order);
    List<SubOrder> getByProduct(Product product);
    void delete(SubOrder subOrder);
}
