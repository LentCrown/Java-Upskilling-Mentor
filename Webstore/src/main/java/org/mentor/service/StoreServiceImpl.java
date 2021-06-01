package org.mentor.service;

import org.mentor.dao.ClientDao;
import org.mentor.dao.ReceiptDao;
import org.mentor.dao.ProductDao;
import org.mentor.model.Client;
import org.mentor.model.Product;
import org.mentor.model.Receipt;
import org.mentor.configuration.Constraints;
import org.mentor.util.IOUtils;
import org.mentor.dao.JPAEntityManager;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

@Service
public class StoreServiceImpl implements StoreService{

    private final ReceiptDao receiptDao;
    private final ClientDao clientDao;
    private final ProductDao productDao;
    private final Scanner scanner;
    private final EntityManager entityManager;
    private final String[] receiptRows = {"id", "bought", "totalPrice", "client_id", "product_id"};
    private final String[] productRows = {"id", "desc", "price_for_piece", "stored"};
    private final String[] clientRows = {"id", "name", "surname", "phone_number"};

    public StoreServiceImpl(ReceiptDao receiptDao, ClientDao clientDao, ProductDao productDao) {
        this.receiptDao = receiptDao;
        this.clientDao = clientDao;
        this.productDao = productDao;
        this.scanner = new Scanner(System.in);
        this.entityManager = JPAEntityManager.getInstance().getEntityManager();
    }

    @Override
    public void createProduct() {
        Product product = new Product();

        product.setDesc(IOUtils.readWords("desc", Constraints.REGEX_SENTENCE));
        product.setStored(IOUtils.readInteger("stored", Constraints.REGEX_NUMBERS));
        product.setPrice(IOUtils.readDouble("price", Constraints.REGEX_PRICE));

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.flush();
            entityManager.getTransaction().commit();
            System.out.println("Product created.");
        }
        catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Transaction has been cancelled");
        }
        finally {
            System.out.println();
        }
    }

    @Override
    public void createClient() {
        Client client = new Client();

        client.setName(IOUtils.readWords("name", Constraints.REGEX_WORD_SINGLE));
        client.setSurname(IOUtils.readWords("surname", Constraints.REGEX_WORD_SINGLE));
        client.setPhone_number(IOUtils.readWords("phone", Constraints.REGEX_PHONE));

        try{
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.flush();
            entityManager.getTransaction().commit();
            System.out.println("\nClient created.");
        }
        catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            System.out.println("\nTransaction has been cancelled");
            e.printStackTrace();
        }
    }

    @Override
    public void createReceipt() {
        if (productDao.findAll().isEmpty() || clientDao.findAll().isEmpty()){
            System.out.println("No clients and(or) products. Create at least one for each");
            return;
        }
        Receipt receipt = new Receipt();
        readProduct();
        Product product = productDao.findById(IOUtils.readInteger("id_merch", Constraints.REGEX_NUMBERS));
        if(product==null){
            System.out.println("No such product");
            return;
        }
        receipt.setProduct(product);
        readClient();
        Client client = clientDao.findById(IOUtils.readInteger("id_client", Constraints.REGEX_NUMBERS));
        if(client==null){
            System.out.println("No such product");
            return;
        }
        receipt.setClient(client);
        receipt.setBought(IOUtils.readInteger("amount", Constraints.REGEX_NUMBERS));
        receipt.setTotalPrice(IOUtils.readDouble("totalPrice", Constraints.REGEX_PRICE));
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(receipt);
            entityManager.flush();
            entityManager.getTransaction().commit();
            System.out.println("\nClient created.");
        }
        catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            System.out.println("\nTransaction has been cancelled");
            e.printStackTrace();
        }
    }

    @Override
    public void readProduct() {
        List<String> queryResult = IOUtils.stringListProducts(productDao.findAll());
        if (queryResult == null) {
            System.out.println("No products");
            return;
        }
        IOUtils.displayQueryTable(queryResult, productRows);
    }

    @Override
    public void readClient() {
        List<String> queryResult = IOUtils.stringListClients(clientDao.findAll());
        if (queryResult == null) {
            System.out.println("No clients");
            return;
        }
        IOUtils.displayQueryTable(queryResult, clientRows);
    }

    @Override
    public void readReceipt() {
        List<String> queryResult = IOUtils.stringListReceipts(receiptDao.findAll());
        if (queryResult == null){
            System.out.println("No receipts");
            return;
        }
        IOUtils.displayQueryTable(queryResult, receiptRows);
    }

    @Override
    public void updateProduct() {
        Product product = productDao.findById(IOUtils.readInteger("id", Constraints.REGEX_NUMBERS));
        if(product==null){
            System.out.println("No such product");
            return;
        }
        if(IOUtils.readAnswer("desc"))
            product.setDesc(IOUtils.readWords("desc",Constraints.REGEX_WORD_SINGLE));
        if(IOUtils.readAnswer("price"))
            product.setPrice(IOUtils.readDouble("price",Constraints.REGEX_WORD_SINGLE));
        if(IOUtils.readAnswer("stored"))
            product.setStored(IOUtils.readInteger("stored",Constraints.REGEX_WORD_SINGLE));

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.flush();
            entityManager.getTransaction().commit();
            System.out.println("Product has been updated");
        }
        catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Transaction has been cancelled");
        }
    }

    @Override
    public void updateClient() {
        Client client = clientDao.findById(IOUtils.readInteger("id", Constraints.REGEX_NUMBERS));
        if(client==null){
            System.out.println("No such client");
            return;
        }

        if(IOUtils.readAnswer("name"))
            client.setName(IOUtils.readWords("name",Constraints.REGEX_WORD_SINGLE));
        if(IOUtils.readAnswer("surname"))
            client.setSurname(IOUtils.readWords("surname",Constraints.REGEX_WORD_SINGLE));
        if(IOUtils.readAnswer("phone_number"))
            client.setPhone_number(IOUtils.readWords("phone_number",Constraints.REGEX_WORD_SINGLE));

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(client);
            entityManager.flush();
            entityManager.getTransaction().commit();
            System.out.println("Client has been updated");
        }
        catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Transaction has been cancelled");
        }
    }

    @Override
    public void updateReceipt() {
        Receipt receipt = receiptDao.findById(IOUtils.readInteger("id", Constraints.REGEX_NUMBERS));
        if(receipt==null){
            System.out.println("No such receipt");
            return;
        }

        if(IOUtils.readAnswer("product_id")) {
            readProduct();
            receipt.setProduct(productDao.findById(IOUtils.readInteger("product_id", Constraints.REGEX_NUMBERS)));
        }
        if(IOUtils.readAnswer("client_id")) {
            readClient();
            receipt.setClient(clientDao.findById(IOUtils.readInteger("client_id", Constraints.REGEX_NUMBERS)));
        }
        if(IOUtils.readAnswer("amount"))
            receipt.setBought(IOUtils.readInteger("bought", Constraints.REGEX_NUMBERS));
        if(IOUtils.readAnswer("totalPrice"))
            receipt.setTotalPrice(IOUtils.readDouble("totalPrice", Constraints.REGEX_PRICE));

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(receipt);
            entityManager.flush();
            entityManager.getTransaction().commit();
            System.out.println("Product was updated.");
        }
        catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Transaction has been cancelled");
        }
    }

    @Override
    public void deleteProduct() {
        Product product = productDao.findById(IOUtils.readInteger("choice", Constraints.REGEX_NUMBERS));
        if(product==null){
            System.out.println("No such product");
            return;
        }
        List<Receipt> receiptList = receiptDao.findByProduct(product);
        try{
            entityManager.getTransaction().begin();
            for(Receipt receipt:receiptList)
                entityManager.remove(receipt);
            entityManager.remove(product);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Transaction has been cancelled");
        }
    }

    @Override
    public void deleteClient() {
        Client client = clientDao.findById(IOUtils.readInteger("choice", Constraints.REGEX_NUMBERS));
        if(client==null){
            System.out.println("No such client");
            return;
        }
        List<Receipt> receiptList = receiptDao.findByClient(client);
        try{
            entityManager.getTransaction().begin();
            for(Receipt receipt:receiptList)
                entityManager.remove(receipt);
            entityManager.remove(client);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Transaction has been cancelled");
        }
    }

    @Override
    public void deleteReceipt() {
        Receipt receipt = receiptDao.findById(IOUtils.readInteger("choice", Constraints.REGEX_NUMBERS));
        if(receipt==null){
            System.out.println("No such receipt");
            return;
        }
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(receipt);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Transaction has been cancelled");
        }
    }
}
