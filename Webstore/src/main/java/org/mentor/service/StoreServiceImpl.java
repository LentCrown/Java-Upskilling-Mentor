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
        System.out.println();
        System.out.println("Creating new product");
        Product product = new Product();
        product.setDesc(IOUtils.readWords("desc", Constraints.REGEX_SENTENCE));
        product.setStored(IOUtils.readInteger("stored", Constraints.REGEX_NUMBERS));
        product.setPrice(IOUtils.readDouble("price", Constraints.REGEX_PRICE));
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        try {
            entityManager.getTransaction().commit();
            System.out.println("Product created.");
        }
        catch (IllegalStateException | RollbackException e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            System.out.println("Transaction has been cancelled");
        }
        finally {
            System.out.println();
        }
    }

    @Override
    public void createClient() {
        System.out.println();
        System.out.println("Creating new client");
        Client client = new Client();

        client.setName(IOUtils.readWords("name", Constraints.REGEX_WORD_SINGLE));
        client.setSurname(IOUtils.readWords("surname", Constraints.REGEX_WORD_SINGLE));
        client.setPhone_number(IOUtils.readWords("phone", Constraints.REGEX_PHONE));

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        try {
            entityManager.getTransaction().commit();
            System.out.println("Client created.");
        }
        catch (IllegalStateException | RollbackException e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            System.out.println("Transaction has been cancelled");
        }
        finally {
            System.out.println();
        }
    }

    @Override
    public void createOrder() {
        System.out.println();
        System.out.println("Creating new order");
        List<Product> productList = productDao.findAll();
        List<Client> clientList = clientDao.findAll();
        if(productList.isEmpty() || clientList.isEmpty()){
            System.out.println("No products and(or) clients in system.. Add them first");
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
        try {
            entityManager.getTransaction().commit();
            System.out.println("Order created.");
        }
        catch (IllegalStateException | RollbackException e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            System.out.println("Transaction has been cancelled");
        }
        finally {
            System.out.println();
        }
    }

    @Override
    public void readProduct() {
        System.out.println();
        List<Product> productList = productDao.findAll();
        if(productList.isEmpty()){
            System.out.println("No products in system");
            return;
        }
        System.out.println("SELECT * FROM PRODUCTS;");
        String[] rowNames = RflctUtilts.getClassFields(Product.class);
        IOUtils.displayQueryTable(productList, rowNames);
        System.out.println();
    }

    @Override
    public void readClient() {
        System.out.println();
        List<Client> clientList = clientDao.findAll();
        if(clientList.isEmpty()){
            System.out.println("No clients in system");
            return;
        }
        System.out.println("SELECT * FROM CLIENTS;");
        String[] rowNames = RflctUtilts.getClassFields(Client.class);
        IOUtils.displayQueryTable(clientList, rowNames);
        System.out.println();
    }

    @Override
    public void readOrder() {
        System.out.println();
        List<Order> orderList = orderDao.findAll();
        if(orderList.isEmpty()){
            System.out.println("No orders in system");
            return;
        }
        System.out.println("SELECT * FROM ORDERS;");
        String[] rowNames = RflctUtilts.getClassFields(Order.class);
        IOUtils.displayQueryTable(orderList, rowNames);
        System.out.println();
    }

    @Override
    public void updateProduct() {
        System.out.println();
        System.out.println("Updating product..");
        System.out.println("Select product to update");
        IOUtils.displayQueryChoice(productDao.findAll());
        Product product = productDao.findById(IOUtils.readInteger("id", Constraints.REGEX_NUMBERS));

        if(IOUtils.readAnswer("desc"))
            product.setDesc(IOUtils.readWords("desc",Constraints.REGEX_WORD_SINGLE));
        if(IOUtils.readAnswer("price"))
            product.setPrice(IOUtils.readDouble("price",Constraints.REGEX_WORD_SINGLE));
        if(IOUtils.readAnswer("stored"))
            product.setStored(IOUtils.readInteger("stored",Constraints.REGEX_WORD_SINGLE));

        entityManager.getTransaction().begin();
        entityManager.merge(product);
        try {
            entityManager.getTransaction().commit();
            System.out.println("Product has been updated");
        }
        catch (IllegalStateException | RollbackException e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            System.out.println("Transaction has been cancelled");
        }
        finally {
            System.out.println();
        }
    }

    @Override
    public void updateClient() {
        System.out.println();
        System.out.println("Updating client..");
        System.out.println("Select client to update");
        IOUtils.displayQueryChoice(clientDao.findAll());
        Client client = clientDao.findById(IOUtils.readInteger("id", Constraints.REGEX_NUMBERS));

        if(IOUtils.readAnswer("name"))
            client.setName(IOUtils.readWords("name",Constraints.REGEX_WORD_SINGLE));
        if(IOUtils.readAnswer("surname"))
            client.setSurname(IOUtils.readWords("surname",Constraints.REGEX_WORD_SINGLE));
        if(IOUtils.readAnswer("phone_number"))
            client.setPhone_number(IOUtils.readWords("phone_number",Constraints.REGEX_WORD_SINGLE));

        entityManager.getTransaction().begin();
        entityManager.merge(client);
        try {
            entityManager.getTransaction().commit();
            System.out.println("Client has been updated");
        }
        catch (IllegalStateException | RollbackException e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            System.out.println("Transaction has been cancelled");
        }
        finally {
            System.out.println();
        }
    }

    @Override
    public void updateOrder() {
        System.out.println();
        System.out.println("Updating order..");
        System.out.println("Select order to update");
        IOUtils.displayQueryChoice(orderDao.findAll());
        Order order = orderDao.findById(IOUtils.readInteger("id", Constraints.REGEX_NUMBERS));

        if(IOUtils.readAnswer("id_merch")) {
            IOUtils.displayQueryChoice(productDao.findAll());
            order.setProduct(productDao.findById(IOUtils.readInteger("id_merch", Constraints.REGEX_NUMBERS)));
        }
        if(IOUtils.readAnswer("id_client")) {
            IOUtils.displayQueryChoice(clientDao.findAll());
            order.setClient(clientDao.findById(IOUtils.readInteger("id_client", Constraints.REGEX_NUMBERS)));
        }
        if(IOUtils.readAnswer("amount"))
            order.setHowMuchBuying(IOUtils.readInteger("amount", Constraints.REGEX_NUMBERS));
        if(IOUtils.readAnswer("totalPrice"))
            order.setTotalPrice(IOUtils.readDouble("totalPrice", Constraints.REGEX_PRICE));

        entityManager.getTransaction().begin();
        entityManager.merge(order);
        try {
            entityManager.getTransaction().commit();
            System.out.println("Product was updated.");
        }
        catch (IllegalStateException | RollbackException e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            System.out.println("Transaction has been cancelled");
        }
        finally {
            System.out.println();
        }
    }

    @Override
    public void deleteProduct() {
        IOUtils.displayQueryChoice(productDao.findAll());
        Product product = productDao.findById(IOUtils.readInteger("choice", Constraints.REGEX_NUMBERS));
        entityManager.getTransaction().begin();
        entityManager.remove(product);
        try {
            entityManager.getTransaction().commit();
            System.out.println("Product was deleted.");
        }
        catch (IllegalStateException | RollbackException e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            System.out.println("Transaction has been cancelled");
        }
        finally {
            System.out.println();
        }
    }

    @Override
    public void deleteClient() {
        IOUtils.displayQueryChoice(clientDao.findAll());
        Client client = clientDao.findById(IOUtils.readInteger("choice", Constraints.REGEX_NUMBERS));
        entityManager.getTransaction().begin();
        entityManager.remove(client);
        try {
            entityManager.getTransaction().commit();
            System.out.println("Client was deleted.");
        }
        catch (IllegalStateException | RollbackException e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            System.out.println("Transaction has been cancelled");
        }
        finally {
            System.out.println();
        }
    }

    @Override
    public void deleteOrder() {
        IOUtils.displayQueryChoice(orderDao.findAll());
        Order order = orderDao.findById(IOUtils.readInteger("choice", Constraints.REGEX_NUMBERS));
        entityManager.getTransaction().begin();
        entityManager.remove(order);
        try {
            entityManager.getTransaction().commit();
            System.out.println("Order has been deleted.");
        }
        catch (IllegalStateException | RollbackException e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            System.out.println("Transaction has been cancelled");
        }
        finally {
            System.out.println();
        }
    }
}
