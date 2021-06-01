package org.mentor.dao;

import org.mentor.repository.Client;
import org.mentor.repository.Product;
import org.mentor.repository.Receipt;

import java.util.List;

public interface ReceiptDao {
    List<Receipt> findAll();
    Receipt findById(Integer id);
    List<Receipt> findByClient(Client client);
    List<Receipt> findByProduct(Product product);
}
