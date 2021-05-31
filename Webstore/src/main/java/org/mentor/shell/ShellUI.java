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
    @ShellMethod(value = "Create product", key = {"c product"})
    public void createProduct() {
        storeService.createProduct();
    }

    @Override
    @ShellMethod(value = "Create client", key = {"c client"})
    public void createClient() {
        storeService.createClient();
    }

    @Override
    @ShellMethod(value = "Create order", key = {"c order"})
    public void createOrder() {
        storeService.createOrder();
    }

    @Override
    @ShellMethod(value = "Read products", key = {"r product"})
    public void readProduct() {
        storeService.readProduct();
    }

    @Override
    @ShellMethod(value = "Read clients", key = {"r client"})
    public void readClient() {
        storeService.readClient();
    }

    @Override
    @ShellMethod(value = "Read orders", key = {"r order"})
    public void readOrder() {
        storeService.readOrder();
    }

    @Override
    @ShellMethod(value = "Update product", key = {"u product"})
    public void updateProduct() {
        storeService.updateProduct();
    }

    @Override
    @ShellMethod(value = "Update client", key = {"u client"})
    public void updateClient() {
        storeService.updateClient();
    }

    @Override
    @ShellMethod(value = "Update order", key = {"u order"})
    public void updateOrder() {
        storeService.updateOrder();
    }

    @Override
    @ShellMethod(value = "Delete product", key = {"d product"})
    public void deleteProduct() {
        storeService.deleteProduct();
    }

    @Override
    @ShellMethod(value = "Delete client", key = {"d client"})
    public void deleteClient() {
        storeService.deleteClient();
    }

    @Override
    @ShellMethod(value = "Delete order", key = {"d order"})
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
