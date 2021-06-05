package org.mentor.service;

import org.mentor.dao.*;
import org.mentor.domain.Pack;
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

    public StoreServiceImpl(OrderDao orderDao, UserDao userDao, ProductDao productDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }

    @Override
    public Boolean createOrder(List<Pack> packList) {
        Double totalPrice = 0.00;

        User user = userDao.findById(1);
        Order order = new Order();
        order.setUser(user);
        for(Pack pack:packList) {
            Product product = productDao.findById(pack.getProduct_id());
            order.getProducts().add(product);
            totalPrice = totalPrice + product.getPrice() * pack.getAmount();
            Integer warehouse_stored = product.getStored();
            product.setStored(warehouse_stored-pack.getAmount());
            productDao.save(product);
        }
        order.setTotalPrice(totalPrice);
        order.setLastEdited();
        orderDao.save(order);
        return true;
    }

    @Override
    public Boolean createUser(List<String> answers) {
        User new_user = new User();
        new_user.setName(answers.get(2));
        new_user.setSurname(answers.get(3));
        new_user.setPhone_number(answers.get(4));
        userDao.save(new_user);
        if (isFirstUser) isFirstUser=false;
        return true;
    }

    @Override
    public Boolean createProduct(List<String> answers) {
        Product product = new Product();
        product.setDesc(answers.get(0));
        product.setPrice(Double.parseDouble(answers.get(1)));
        product.setStored(Integer.parseInt(answers.get(2)));

        productDao.save(product);
        return true;
    }

    @Override
    public List<Order> readOrders() {
        return null;
    }

    @Override
    public List<Order> readUserOrders() {
        return null;
    }

    @Override
    public List<Product> readProducts() { return productDao.findAll(); }

    @Override
    public Boolean deleteOrder(Integer order_id) {
        Order order = orderDao.findById(order_id);
        orderDao.delete(order);
        return true;
    }

    @Override
    public Boolean deleteProduct(Integer product_id) {
        Product product = productDao.findById(product_id);
        List<Order> orders = orderDao.findByProduct(product);
        for(Order order:orders)
            orderDao.delete(order);
        productDao.delete(product);
        return true;
    }


}
