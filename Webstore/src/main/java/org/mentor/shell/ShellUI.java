package org.mentor.shell;

import org.mentor.service.StoreService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.sql.SQLException;

@ShellComponent
public class ShellUI implements StoreService {
    private static boolean webUILaunched = false;
    private final StoreService storeService;

    public ShellUI(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    @ShellMethod(value = "Create product", key = {"insert product"})
    public void createProduct() {
        storeService.createProduct();
    }

    @Override
    @ShellMethod(value = "Create client", key = {"insert client"})
    public void createClient() {
        storeService.createClient();
    }

    @Override
    @ShellMethod(value = "Create order", key = {"insert order"})
    public void createOrder() {
        storeService.createOrder();
    }

    @Override
    @ShellMethod(value = "Read products", key = {"read products"})
    public void readProduct() {
        storeService.readProduct();
    }

    @Override
    @ShellMethod(value = "Read clients", key = {"read clients"})
    public void readClient() {
        storeService.readClient();
    }

    @Override
    @ShellMethod(value = "Read orders", key = {"read orders"})
    public void readOrder() {
        storeService.readOrder();
    }

    @Override
    @ShellMethod(value = "Update product", key = {"update product"})
    public void updateProduct() {
        storeService.updateProduct();
    }

    @Override
    @ShellMethod(value = "Update client", key = {"update client"})
    public void updateClient() {
        storeService.updateClient();
    }

    @Override
    @ShellMethod(value = "Update order", key = {"update order"})
    public void updateOrder() {
        storeService.updateOrder();
    }

    @Override
    @ShellMethod(value = "Delete product", key = {"delete product"})
    public void deleteProduct() {
        storeService.deleteProduct();
    }

    @Override
    @ShellMethod(value = "Delete client", key = {"delete client"})
    public void deleteClient() {
        storeService.deleteClient();
    }

    @Override
    @ShellMethod(value = "Delete order", key = {"delete order"})
    public void deleteOrder() {
        storeService.deleteOrder();
    }

    @ShellMethod(value = "Open H2 WebUI ", key = {"web"})
    public void openH2WebUI(){
        if (!webUILaunched) {
            try {
                org.h2.tools.Console.main();
                webUILaunched = true;
                System.out.println("WebUI will be opened in your browser");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
