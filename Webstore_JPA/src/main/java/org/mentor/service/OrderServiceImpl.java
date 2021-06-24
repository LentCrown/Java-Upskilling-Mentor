package org.mentor.service;

import org.mentor.dao.OrderDao;
import org.mentor.domain.Pack;
import org.mentor.domain.UserSession;
import org.mentor.exceptions.TriedToPurchaseMoreThanStoredException;
import org.mentor.model.Order;
import org.mentor.model.Product;
import org.mentor.model.SubOrder;
import org.mentor.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final UserService userService;
    private final ProductService productService;
    private final SubOrderService subOrderService;

    public OrderServiceImpl(OrderDao orderDao, UserService userService, ProductService productService, SubOrderService subOrderService) {
        this.orderDao = orderDao;
        this.userService = userService;
        this.productService = productService;
        this.subOrderService = subOrderService;
    }

    @Override
    public void create(List<Pack> packList) throws TriedToPurchaseMoreThanStoredException {
        double totalPrice = 0.00;

        User user = userService.getById(1);
        Order order = new Order();
        order.setUser(user);
        for(Pack pack:packList) {
            Product product = productService.getById(pack.getProduct_id());
            int amount = pack.getAmount();
            if (product.getStored()<amount) throw new TriedToPurchaseMoreThanStoredException("Tried to purchase " +
                    "" + amount + " of " + product.getDesc() + "\nIn warehouse: " + product.getStored());

            Double sub_price = amount * product.getPrice();
            totalPrice = totalPrice + sub_price;
            product.setStored(product.getStored()-amount);
            productService.save(product);
            order.addProduct(product, amount, sub_price);
        }
        order.setTotalPrice(totalPrice);
        order.setLastEdited();
        orderDao.save(order);
        subOrderService.save(packList, order);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.findAll();
    }

    @Override
    public List<Order> getUserOrders(UserSession userSession) {
        User user = userService.getById(userSession.getId());
        List<Order> orders = orderDao.findByUser(user);
        return orders;
    }

    @Override
    public void delete(Integer order_id) {
        Order order = orderDao.findById(order_id);
        List<SubOrder> subOrders = subOrderService.getByOrder(order);
        if (subOrders != null && !subOrders.isEmpty()) subOrders.forEach(s -> subOrderService.delete(s));
        orderDao.delete(order);
    }
}
