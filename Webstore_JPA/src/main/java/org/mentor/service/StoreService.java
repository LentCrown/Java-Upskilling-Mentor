package org.mentor.service;


import org.mentor.domain.Pack;
import org.mentor.domain.UserSession;
import org.mentor.model.Order;
import org.mentor.model.Product;
import org.mentor.model.User;

import java.util.List;

public interface StoreService {

    Boolean createOrder(List<Pack> packList);
    Boolean createProduct(List<String> answers);
    Boolean createUser(List<String> answers);
    List<Order> readOrders();
    List<Order> readUserOrders();
    List<Product> readProducts();
    Boolean deleteOrder(Integer id);
    Boolean deleteProduct(Integer id);
}
