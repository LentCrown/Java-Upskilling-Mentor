package org.mentor.service;


import org.mentor.domain.Pack;
import org.mentor.domain.UserSession;
import org.mentor.model.Order;
import org.mentor.model.Product;
import org.mentor.model.User;

import java.util.List;

public interface StoreService {
    //ALL
    Boolean createUser(List<String> answers);
    UserSession logon(List<String> answers);
    List<Product> showProducts();
    Boolean checkLoginExisting(String login);
    //LOGGED_IN
    Boolean createOrder(UserSession userSession, List<Pack> packList);
    List<Order> showUserOrders(UserSession userSession);
    UserSession changeLoggedUserInfo(UserSession userSession, List<String> answers);
    UserSession changeLoggedUserCreds(UserSession userSession, List<String> answers);
    //ADMIN
    Boolean createProduct(UserSession userSession, List<String> answers);
    List<User> showUsers(UserSession userSession);
    List<User> showAdmins(UserSession userSession);
    List<User> showClients(UserSession userSession);
    List<Order> showOrders(UserSession userSession);
    Boolean deleteUser(UserSession userSession, Integer id);
    Boolean deleteProduct(UserSession userSession, Integer id);
    Boolean deleteOrder(UserSession userSession, Integer id);
    Boolean revokeAdminAccess(UserSession userSession, Integer id);
    Boolean grantAdminAccess(UserSession userSession, Integer id);
}
