package org.mentor.dao;

import org.mentor.model.User;
import org.mentor.model.Product;
import org.mentor.model.Order;

import java.util.List;

public interface OrderDao {
    void create(Order order);
    List<Order> findAll();
    Order findById(Integer id);
    List<Order> findByClient(User user);
    List<Order> findByProduct(Product product);
    void delete(Order order);
}
