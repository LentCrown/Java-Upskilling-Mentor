package org.mentor.service;

import org.mentor.dao.ClientDao;
import org.mentor.dao.OrderDao;
import org.mentor.dao.ProductDao;
import org.mentor.repository.Client;
import org.mentor.repository.Order;
import org.mentor.repository.Product;
import org.mentor.util.Constraints;
import org.mentor.util.IOUtils;
import org.mentor.util.JPAEntityManager;
import org.mentor.util.RflctUtilts;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class StoreServiceImpl implements StoreService{

    private final ProductDao productDao;
    private final OrderDao orderDao;
    private final ClientDao clientDao;
    private final Scanner scanner;
    private final EntityManager entityManager;

    public StoreServiceImpl(ProductDao productDao, OrderDao orderDao, ClientDao clientDao) {
        this.productDao = productDao;
        this.orderDao = orderDao;
        this.clientDao = clientDao;
        this.scanner = new Scanner(System.in);
        this.entityManager = JPAEntityManager.getInstance().getEntityManager();
    }

    @Override
    public void createProduct() {
        System.out.println("Creating new product");
        Product product = new Product();
        product.setDesc(IOUtils.readWords("desc", Constraints.REGEX_SENTENCE));
        product.setStored(IOUtils.readInteger("stored", Constraints.REGEX_NUMBERS));
        product.setPrice(IOUtils.readDouble("name", Constraints.REGEX_PRICE));
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        try { entityManager.getTransaction().commit(); }
        catch (IllegalStateException | RollbackException e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void createClient() {
        System.out.println("Creating new client");
        Client client = new Client();

        client.setName(IOUtils.readWords("name", Constraints.REGEX_WORD_SINGLE));
        client.setSurname(IOUtils.readWords("surname", Constraints.REGEX_WORD_SINGLE));
        client.setPhone_number(IOUtils.readWords("phone", Constraints.REGEX_PHONE));

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        try { entityManager.getTransaction().commit(); }
        catch (IllegalStateException | RollbackException e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void createOrder() {
        List<Product> productList = productDao.findAll();
        List<Client> clientList = clientDao.findAll();
        if(productList.isEmpty() || clientList.isEmpty()){
            System.out.println("No products and clients in system.. Add them first");
            return;
        }

        System.out.println("Creating new order");
        Order order = new Order();
        IOUtils.displayQueryChoice(productDao.findAll());
        order.setProduct(productDao.findById(IOUtils.readInteger("id_merch", Constraints.REGEX_NUMBERS)));
        IOUtils.displayQueryChoice(clientDao.findAll());
        order.setClient(clientDao.findById(IOUtils.readInteger("id_client", Constraints.REGEX_NUMBERS)));
        order.setHowMuchBuying(IOUtils.readInteger("amount", Constraints.REGEX_NUMBERS));
        order.setTotalPrice(IOUtils.readDouble("totalPrice", Constraints.REGEX_PRICE));

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        try { entityManager.getTransaction().commit(); }
        catch (IllegalStateException | RollbackException e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void readProduct() {
        List<Product> productList = productDao.findAll();
        if(productList.isEmpty()){
            System.out.println("No products in system");
            return;
        }
        System.out.println("SELECT * FROM PRODUCTS;");
        String[] rowNames = RflctUtilts.getClassFields(Product.class);
        IOUtils.displayQueryTable(productList, rowNames);
    }

    @Override
    public void readClient() {
        List<Client> clientList = clientDao.findAll();
        if(clientList.isEmpty()){
            System.out.println("No clients in system");
            return;
        }
        System.out.println("SELECT * FROM CLIENTS;");
        String[] rowNames = RflctUtilts.getClassFields(Client.class);
        IOUtils.displayQueryTable(clientList, rowNames);
    }

    @Override
    public void readOrder() {
        List<Order> orderList = orderDao.findAll();
        if(orderList.isEmpty()){
            System.out.println("No orders in system");
            return;
        }
        System.out.println("SELECT * FROM ORDERS;");
        String[] rowNames = RflctUtilts.getClassFields(Order.class);
        IOUtils.displayQueryTable(orderList, rowNames);
    }

    @Override
    public void updateProduct() {
    }

    @Override
    public void updateClient() {

    }

    @Override
    public void updateOrder() {
        Order order = orderDao.findById(1);
        if(IOUtils.readAnswer("merch_id")) {
            IOUtils.displayQueryChoice(productDao.findAll());
            order.setProduct(productDao.findById(IOUtils.readInteger("id_merch", Constraints.REGEX_NUMBERS)));
        }
        if(IOUtils.readAnswer("client_id")) {
            IOUtils.displayQueryChoice(clientDao.findAll());
            order.setClient(clientDao.findById(IOUtils.readInteger("id_client", Constraints.REGEX_NUMBERS)));
        }
        if(IOUtils.readAnswer("amount"))
            order.setHowMuchBuying(IOUtils.readInteger("amount", Constraints.REGEX_NUMBERS));
        if(IOUtils.readAnswer("totalPrice"))
            order.setTotalPrice(IOUtils.readDouble("totalPrice", Constraints.REGEX_PRICE));
    }

    @Override
    public void deleteProduct() {
        IOUtils.displayQueryChoice(productDao.findAll());
        Product product = productDao.findById(IOUtils.readInteger("choice", Constraints.REGEX_NUMBERS));
        entityManager.getTransaction().begin();
        entityManager.remove(product);
        try { entityManager.getTransaction().commit(); }
        catch (IllegalStateException | RollbackException e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void deleteClient() {
        IOUtils.displayQueryChoice(clientDao.findAll());
        Client client = clientDao.findById(IOUtils.readInteger("choice", Constraints.REGEX_NUMBERS));
        entityManager.getTransaction().begin();
        entityManager.remove(client);
        try { entityManager.getTransaction().commit(); }
        catch (IllegalStateException | RollbackException e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void deleteOrder() {
        IOUtils.displayQueryChoice(orderDao.findAll());
        Order order = orderDao.findById(IOUtils.readInteger("choice", Constraints.REGEX_NUMBERS));
        entityManager.getTransaction().begin();
        entityManager.remove(order);
        try { entityManager.getTransaction().commit(); }
        catch (IllegalStateException | RollbackException e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
