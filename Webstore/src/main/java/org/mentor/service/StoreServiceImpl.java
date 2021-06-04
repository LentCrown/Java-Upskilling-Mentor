package org.mentor.service;

import org.mentor.dao.*;
import org.mentor.domain.Pack;
import org.mentor.domain.UserSession;
import org.mentor.model.Credential;
import org.mentor.model.Order;
import org.mentor.model.Product;
import org.mentor.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService{
    private static boolean isFirstUser = true;
    private final OrderDao orderDao;
    private final UserDao userDao;
    private final ProductDao productDao;
    private final CredentialsDao credentialsDao;

    public StoreServiceImpl(OrderDao orderDao, UserDao userDao, ProductDao productDao, CredentialsDao credentialsDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.productDao = productDao;
        this.credentialsDao = credentialsDao;
    }

    @Override
    public Boolean createUser(List<String> answers) {
        User new_user = new User();
        Credential credential = new Credential();
        credential.setLogin(answers.get(0));
        credential.setPassword(answers.get(1));
        credential.setIs_admin(isFirstUser);
        new_user.setName(answers.get(2));
        new_user.setSurname(answers.get(3));
        new_user.setPhone_number(answers.get(4));
        new_user.setCredential(credential);
        userDao.create(new_user);
        if (isFirstUser) isFirstUser=false;
        return true;
    }

    @Override
    public UserSession logon(List<String> answer) {
        Integer user_id = credentialsDao.findIdByLogin(answer.get(0));
        if (user_id == null) {
            return null;
        }
        User user_candidate = userDao.findById(user_id);
        if (user_candidate.getCredential().getPassword().equals(answer.get(1)))
            return new UserSession(user_candidate.getId(), user_candidate.getName(), user_candidate.getSurname());
        return null;
    }

    @Override
    public List<Product> showProducts() { return productDao.findAll(); }

    @Override
    public Boolean checkLoginExisting(String login) {
        Integer id = credentialsDao.findIdByLogin(login);
        return id != null;
    }

    @Override
    public Boolean createOrder(UserSession userSession, List<Pack> packList) {
        Double totalPrice = 0.00;

        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        Order order = new Order();
        order.setUser(user);
        for(Pack pack:packList) {
            Product product = productDao.findById(pack.getProduct_id());
            order.getProducts().add(product);
            totalPrice = totalPrice + product.getPrice() * pack.getAmount();
            Integer warehouse_stored = product.getStored();
            product.setStored(warehouse_stored-pack.getAmount());
            productDao.update(product);
        }
        order.setTotalPrice(totalPrice);
        order.setLastEdited();
        orderDao.create(order);
        return true;
    }

    @Override
    public List<Order> showUserOrders(UserSession userSession) {
        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        return orderDao.findByClient(user);
    }

    @Override
    public UserSession changeLoggedUserInfo(UserSession userSession, List<String> answers) {
        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        if(!answers.get(0).equals("")){
            user.setName(answers.get(0));
            userSession.setName(answers.get(0));
        }
        if(!answers.get(1).equals("")){
            user.setSurname(answers.get(1));
            userSession.setSurname(answers.get(1));
        }
        if(!answers.get(2).equals("")) user.setPhone_number(answers.get(2));
        userDao.update(user);
        return userSession;
    }

    @Override
    public UserSession changeLoggedUserCreds(UserSession userSession, List<String> answers) {
        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        if(!answers.get(3).equals(""))
            user.getCredential().setLogin(answers.get(3));
        if(!answers.get(4).equals("")) user.getCredential().setPassword(answers.get(4));
        userDao.update(user);
        return userSession;
    }

    @Override
    public Boolean createProduct(UserSession userSession, List<String> answers) {
        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        if(!user.getCredential().getIs_admin()) return false;
        Product product = new Product();
        product.setDesc(answers.get(0));
        product.setPrice(Double.parseDouble(answers.get(1)));
        product.setStored(Integer.parseInt(answers.get(2)));

        productDao.create(product);
        return true;
    }

    @Override
    public List<User> showUsers(UserSession userSession) {
        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        if(!user.getCredential().getIs_admin()) return new ArrayList<>();
        return userDao.findAll();
    }

    @Override
    public List<User> showAdmins(UserSession userSession) {
        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        if(!user.getCredential().getIs_admin()) return null;
        return userDao.findAdmins();
    }

    @Override
    public List<User> showClients(UserSession userSession) {
        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        if(!user.getCredential().getIs_admin()) return null;
        return userDao.findClients();
    }

    @Override
    public List<Order> showOrders(UserSession userSession) {
        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        if(!user.getCredential().getIs_admin()) return new ArrayList<>();
        return orderDao.findAll();
    }

    @Override
    public Boolean deleteUser(UserSession userSession, Integer user_id) {
        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        if(!user.getCredential().getIs_admin()) return false;
        if(user_id == 1) return false;
        User userToDelete = userDao.findById(user_id);
        List<Order> orderList = orderDao.findByClient(userToDelete);
        for (Order order:orderList)
            orderDao.delete(order);
        userDao.delete(userToDelete);
        return true;
    }

    @Override
    public Boolean deleteProduct(UserSession userSession,Integer product_id) {
        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        if(!user.getCredential().getIs_admin()) return false;
        Product product = productDao.findById(product_id);

        List<Order> orders = orderDao.findByProduct(product);
        for(Order order:orders)
            orderDao.delete(order);
        productDao.delete(product);
        return true;
    }

    @Override
    public Boolean deleteOrder(UserSession userSession, Integer order_id) {
        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        if(!user.getCredential().getIs_admin()) return false;
        Order order = orderDao.findById(order_id);
        orderDao.delete(order);
        return true;
    }

    @Override
    public Boolean revokeAdminAccess(UserSession userSession, Integer user_id) {
        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        if(!user.getCredential().getIs_admin()) return false;
        Credential credential = credentialsDao.findById(user_id);
        credential.setIs_admin(false);
        credentialsDao.updateCredential(credential);
        return true;
    }

    @Override
    public Boolean grantAdminAccess(UserSession userSession, Integer user_id) {
        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        if(!user.getCredential().getIs_admin()) return false;
        Credential credential = credentialsDao.findById(user_id);
        credential.setIs_admin(true);
        credentialsDao.updateCredential(credential);
        return true;
    }
}
