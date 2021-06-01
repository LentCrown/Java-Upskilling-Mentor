package org.mentor.service;

import org.mentor.dao.ClientDao;
import org.mentor.dao.ReceiptDao;
import org.mentor.dao.ProductDao;
import org.mentor.repository.Client;
import org.mentor.repository.Product;
import org.mentor.repository.Receipt;
import org.mentor.util.Constraints;
import org.mentor.util.IOUtils;
import org.mentor.util.JPAEntityManager;
import org.mentor.util.RflctUtilts;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class StoreServiceImpl implements StoreService{

    //private final ProductDao productDao;
    private final ReceiptDao receiptDao;
    private final ClientDao clientDao;
    //private final ProductDao productDao;
    private final Scanner scanner;
    private final EntityManager entityManager;

    public StoreServiceImpl(ReceiptDao receiptDao, ClientDao clientDao) {
        this.receiptDao = receiptDao;
        this.clientDao = clientDao;
        //this.productDao = productDao;
        this.scanner = new Scanner(System.in);
        this.entityManager = JPAEntityManager.getInstance().getEntityManager();
    }

    @Override
    public void createProduct() {
    }

    @Override
    public void createClient() {
        Client client1 = new Client("Anton","Avilkin", "+79060374045");
        Client client2 = new Client("Kirill","Avilkin", "+79060374045");

/*        Product product1 = new Product("Grass",100.00, 10);
        Product product2 = new Product("Field",100.00, 10);
        Product product3 = new Product("Pole",100.00, 10);*/

        Receipt receipt1 = new Receipt(10,10.00);
        Receipt receipt2 = new Receipt(20,20.00);
        Receipt receipt3 = new Receipt(30,30.00);
        Receipt receipt4 = new Receipt(40,40.00);

        receipt1.setClient(client1);
        receipt2.setClient(client1);
        receipt3.setClient(client2);
        receipt4.setClient(client2);

/*        receipt1.setProduct(product1);
        receipt2.setProduct(product2);
        receipt3.setProduct(product3);
        receipt4.setProduct(product3);*/

        List<Receipt> receiptList = Arrays.asList(receipt1,receipt2,receipt3,receipt4);

        for (Receipt receipt: receiptList){
            entityManager.getTransaction().begin();
            entityManager.persist(receipt);
            try {
                entityManager.getTransaction().commit();
                System.out.println("Entities created.");
            }
            catch (IllegalStateException | RollbackException e){
                e.printStackTrace();
                entityManager.getTransaction().rollback();
                System.out.println("Transaction has been cancelled");
            }
        }
    }

    @Override
    public void createOrder() {
    }

    @Override
    public void readProduct() {
    }

    @Override
    public void readClient() {
        System.out.println();
        List<Client> clientList = clientDao.findAll();
        if(clientList.isEmpty()){
            System.out.println("No clients in system");
            return;
        }
        String[] rowNames = RflctUtilts.getClassFields(Client.class);
        IOUtils.displayQueryTable(clientList, rowNames);
        System.out.println();
    }

    @Override
    public void readReceipt() {
        System.out.println();
        List<Receipt> receiptList = receiptDao.findAll();
        if(receiptList.isEmpty()){
            System.out.println("No orders in system");
            return;
        }
        String[] rowNames = RflctUtilts.getClassFields(Receipt.class);
        IOUtils.displayQueryTable(receiptList, rowNames);
        System.out.println();
    }

    @Override
    public void updateProduct() {
    }

    @Override
    public void updateClient() {
    }

    @Override
    public void updateReceipt() {
    }

    @Override
    public void deleteProduct() {

    }

    @Override
    public void deleteClient() {
        IOUtils.displayQueryChoice(clientDao.findAll());
        Client client = clientDao.findById(IOUtils.readInteger("choice", Constraints.REGEX_NUMBERS));
        List<Receipt> receiptList = receiptDao.findByClient(client);
        entityManager.getTransaction().begin();
        for(Receipt receipt:receiptList)
            entityManager.remove(receipt);
        entityManager.remove(client);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteReceipt() {
        IOUtils.displayQueryChoice(receiptDao.findAll());
        Receipt receipt = receiptDao.findById(IOUtils.readInteger("choice", Constraints.REGEX_NUMBERS));
        entityManager.getTransaction().begin();
        entityManager.remove(receipt);
        entityManager.getTransaction().commit();
    }
}
