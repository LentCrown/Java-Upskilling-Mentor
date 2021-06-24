package org.mentor.service;

import org.mentor.domain.Pack;
import org.mentor.domain.UserSession;
import org.mentor.exceptions.TriedToPurchaseMoreThanStoredException;
import org.mentor.model.Order;

import java.util.List;

public interface OrderService {

    void create(List<Pack> packList) throws TriedToPurchaseMoreThanStoredException;
    List<Order> getAll();
    List<Order> getUserOrders(UserSession userSession);
    void delete(Integer order_id);
}
