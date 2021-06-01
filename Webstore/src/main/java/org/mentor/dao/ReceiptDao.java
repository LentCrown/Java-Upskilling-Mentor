package org.mentor.dao;

import org.mentor.model.Client;
import org.mentor.model.Product;
import org.mentor.model.Receipt;

import java.util.List;

public interface ReceiptDao {
    List<Receipt> findAll();
    Receipt findById(Integer id);
    List<Receipt> findByClient(Client client);
    List<Receipt> findByProduct(Product product);
}
