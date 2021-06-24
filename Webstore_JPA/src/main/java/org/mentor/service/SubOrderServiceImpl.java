package org.mentor.service;

import org.mentor.dao.SubOrderDao;
import org.mentor.dao.ProductDao;
import org.mentor.domain.Pack;
import org.mentor.model.Order;
import org.mentor.model.SubOrder;
import org.mentor.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubOrderServiceImpl implements SubOrderService {
    private final ProductDao productDao;
    private final SubOrderDao subOrderDao;

    public SubOrderServiceImpl(ProductDao productDao, SubOrderDao subOrderDao) {
        this.productDao = productDao;
        this.subOrderDao = subOrderDao;
    }

    @Override
    public void save(List<Pack> packList, Order order){
        for(Pack pack:packList) {
            Product product = productDao.findById(pack.getProduct_id());
            SubOrder subOrder = new SubOrder(product.getId(), order.getId(), pack.getAmount(), product.getPrice() * pack.getAmount());
            subOrderDao.save(subOrder);
        }
    }

    @Override
    public List<SubOrder> getByOrder(Order order) {
        return subOrderDao.findByOrder(order);
    }

    @Override
    public List<SubOrder> getByProduct(Product product) {
        return subOrderDao.findByProduct(product);
    }

    @Override
    public void delete(SubOrder subOrder) {
        subOrderDao.delete(subOrder);
    }
}
