package org.mentor.service;

import org.mentor.dao.ProductDao;
import org.mentor.model.Product;
import org.mentor.model.SubOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    private final SubOrderService subOrderService;

    public ProductServiceImpl(ProductDao productDao, SubOrderService subOrderService) {
        this.productDao = productDao;
        this.subOrderService = subOrderService;
    }

    @Override
    public void create(String desc, Double price, Integer stored) {
        Product product = new Product();
        product.setDesc(desc);
        product.setPrice(price);
        product.setStored(stored);
        productDao.save(product);
    }

    @Override
    public Product getById(Integer product_id) {
        return productDao.findById(product_id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void delete(Integer product_id) {
        Product product = productDao.findById(product_id);
        List<SubOrder> subOrders = subOrderService.getByProduct(product);
        if (subOrders != null && !subOrders.isEmpty()) subOrders.forEach(s -> subOrderService.delete(s));
        productDao.delete(product);
    }
}
