package org.mentor.service;

import org.mentor.model.Product;

import java.util.List;

public interface ProductService {

    void create(String desc, Double price, Integer stored);
    Product getById(Integer product_id);
    List<Product> getAll();
    void save(Product product);
    void delete(Integer product_id);

}
