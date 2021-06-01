package org.mentor.dao;

import org.mentor.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();
    Product findById(Integer id);
}
