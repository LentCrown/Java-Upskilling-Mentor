package org.mentor.dao;

import org.mentor.model.Product;

import java.util.List;

public interface ProductDao {
    void create(Product product);
    Product findById(Integer id);
    List<Product> findAll();
    void update(Product product);
    void delete(Product product);
}
