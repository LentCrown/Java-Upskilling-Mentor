package org.mentor.service;

import org.mentor.dao.*;
import org.mentor.domain.Pack;
import org.mentor.domain.UserSession;
import org.mentor.model.Credential;
import org.mentor.model.Order;
import org.mentor.model.Product;
import org.mentor.model.User;
import org.mentor.util.IUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

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
    public Boolean register() {
        String[] userData = IUser.enterUserCredentials();
        String[] userCreds = IUser.enterAuthCredentials();

        User new_user = new User();
        new_user.setName(userData[0]);
        new_user.setSurname(userData[1]);
        new_user.setPhone_number(userData[2]);
        Credential credential = new Credential();
        credential.setLogin(userCreds[0]);
        credential.setPassword(userCreds[1]);
        credential.setIs_admin(isFirstUser);
        new_user.setCredential(credential);

        userDao.create(new_user);
        if (isFirstUser) isFirstUser=false;
        return true;
    }

    @Override
    public UserSession logon() {
        String[] userCreds = IUser.enterAuthCredentials();
        Integer user_id = credentialsDao.findIdByLogin(userCreds[0]);
        if (user_id == null) {
            return null;
        }
        User user_candidate = userDao.findById(user_id);
        if (user_candidate.getCredential().getPassword().equals(userCreds[1]))
            return new UserSession(user_candidate.getId(), user_candidate.getName(), user_candidate.getSurname());
        return null;
    }

    @Override
    public Boolean createOrder(UserSession userSession) {
        Double totalPrice = 0.00;
        User user = userDao.findById(userSession.getId());
        List<Pack> packs = IUser.enterOrderCredentials(productDao.findAll());
        Order order = new Order();
        order.setUser(user);
        for(Pack pack:packs) {
            Product product = pack.getProduct();
            totalPrice = totalPrice + product.getPrice() * pack.getAmount();
            order.getProducts().add(product);
        }
        order.setTotalPrice(totalPrice);
        orderDao.create(order);
        return true;
    }

    @Override
    public Boolean createProduct(UserSession userSession) {
        User user = userDao.findById(userSession.getId());
        if(!user.getCredential().getIs_admin()) {
            return false;
        }
        String[] productCreds = IUser.enterProductCredentials();

        Product product = new Product();
        product.setDesc(productCreds[0]);
        product.setPrice(Double.parseDouble(productCreds[1]));
        product.setStored(Integer.parseInt(productCreds[2]));

        productDao.create(product);
        return true;
    }

    @Override
    public List<Order> showUserOrders(UserSession userSession) {
        return orderDao.findByClient(userDao.findById(userSession.getId()));
    }

    @Override
    public List<Product> showProducts() { return productDao.findAll(); }

    @Override
    public Boolean changeLoggedUserPassword(UserSession userSession) {
        System.out.println("change password");
        User user = userDao.findById(userSession.getId());
        if (user == null) return false;
        Scanner scanner = new Scanner(System.in);
        Credential credential = credentialsDao.findById(user.getId());
        credential.setPassword(scanner.nextLine());
        credentialsDao.updateCredential(credential);
        return true;
    }

    @Override
    public Boolean deleteUser(UserSession userSession) {

        Scanner scanner = new Scanner(System.in);
        Integer userId = scanner.nextInt();
        if(userId == 1) return false;
        User user = userDao.findById(userId);
        if (user == null) return null;
        userDao.delete(user);
        return true;
    }

    @Override
    public Boolean deleteProduct(UserSession userSession) {
        System.out.println("delete product");
        Scanner scanner = new Scanner(System.in);
        Product product = productDao.findById(scanner.nextInt());
        productDao.delete(product);
        return true;
    }

    @Override
    public Boolean deleteOrder(UserSession userSession) {
        System.out.println("delete order");
        Scanner scanner = new Scanner(System.in);
        Order order = orderDao.findById(scanner.nextInt());
        orderDao.delete(order);
        return true;
    }

    @Override
    public Boolean revokeAdminAccess(UserSession userSession) {
        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        if(!user.getCredential().getIs_admin()) return false;
        List<User> admins = userDao.findAdmins();
        for (User admin: admins) System.out.println(admin.toString());
        Scanner scanner = new Scanner(System.in);
        Credential credential = credentialsDao.findById(scanner.nextInt());
        credential.setIs_admin(true);
        credentialsDao.updateCredential(credential);
        return true;
    }

    @Override
    public Boolean grantAdminAccess(UserSession userSession) {
        User user = userDao.findById(userSession.getId());
        if (user == null) return null;
        if(!user.getCredential().getIs_admin()) return false;
        List<User> clients = userDao.findClients();
        for (User client: clients) System.out.println(client.toString());
        Scanner scanner = new Scanner(System.in);
        Credential credential = credentialsDao.findById(scanner.nextInt());
        credential.setIs_admin(true);
        credentialsDao.updateCredential(credential);
        return true;
    }
}
