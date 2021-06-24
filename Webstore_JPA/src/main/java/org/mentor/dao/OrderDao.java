package org.mentor.dao;

import org.mentor.model.Order;
import org.mentor.model.User;

import java.util.List;

public interface OrderDao {
    void save(Order order);
    List<Order> findAll();
    List<Order> findByUser(User user);
    Order findById(Integer id);
    void delete(Order order);
}
