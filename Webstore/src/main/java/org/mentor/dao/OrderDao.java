package org.mentor.dao;

import org.mentor.repository.Order;

import java.util.List;

public interface OrderDao {
    List<Order> findAll();
    Order findById(Integer id);
}
