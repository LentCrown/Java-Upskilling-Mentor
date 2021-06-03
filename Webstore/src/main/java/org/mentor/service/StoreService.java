package org.mentor.service;


import org.mentor.domain.UserSession;
import org.mentor.model.Order;
import org.mentor.model.Product;

import java.util.List;

public interface StoreService {
    //ALL
    Boolean register();
    UserSession logon();
    List<Product> showProducts();
    //LOGGED_IN
    Boolean createOrder(UserSession userSession);
    List<Order> showUserOrders(UserSession userSession);
    Boolean changeLoggedUserPassword(UserSession userSession);
    //ADMIN
    Boolean createProduct(UserSession userSession);
    Boolean deleteUser(UserSession userSession);
    Boolean deleteProduct(UserSession userSession);
    Boolean deleteOrder(UserSession userSession);
    Boolean revokeAdminAccess(UserSession userSession);
    Boolean grantAdminAccess(UserSession userSession);
}
