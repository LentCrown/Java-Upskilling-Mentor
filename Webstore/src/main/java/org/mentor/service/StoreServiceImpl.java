package org.mentor.service;

import org.mentor.repository.Product;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Service
public class StoreServiceImpl implements StoreService{
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public StoreServiceImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("webstore.h2");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void registerNewProduct() {
        entityManager.getTransaction().begin();
        Product product = new Product();
        product.setDesc("Grass");
        product.setPrice(200.00);
        product.setStored(10);
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    @Override
    public void registerNewClient() {
/*        transactional.begin();
        Client client = new Client();
        client.setName("Anton");
        client.setSurname("Avilkin");
        client.setPhone_number("+79060374045");
        transactional.persist(client);
        transactional.commit();*/
    }

    @Override
    public void registerNewOrder() {
/*        transactional.begin();
        Order order = new Order();
        order.setId_client(1);
        order.setId_merch(1);
        order.setHowMuchBuying(10);
        order.setTotalPrice(100.00);
        transactional.persist(order);
        transactional.commit();*/
    }

    @Override
    public void changeProductNumbers() {
        System.out.println("changeProductNumbers");
    }

}
