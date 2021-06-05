package org.mentor.dao;

import org.mentor.model.Product;

import java.util.List;

public interface ProductDao {
    void save(Product product);
    Product findById(Integer id);
    List<Product> findAll();
    void delete(Product product);
}
