package org.mentor.shell;

import org.mentor.service.StoreService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.sql.SQLException;

@ShellComponent
public class ShellUI {
    private static boolean webUILaunched = false;
    private final StoreService storeService;

    public ShellUI(StoreService storeService) {
        this.storeService = storeService;
    }

    @ShellMethod(value = "Create product", key = {"c product"})
    public void createProduct() {
        storeService.createProduct();
    }

    @ShellMethod(value = "Create client", key = {"c client"})
    public void createClient() {
        storeService.createClient();
    }

    @ShellMethod(value = "Create receipt", key = {"c receipt"})
    public void createReceipt() {
        storeService.createReceipt();
    }

    @ShellMethod(value = "Read products", key = {"r product"})
    public void readProduct() { storeService.readProduct(); }

    @ShellMethod(value = "Read clients", key = {"r client"})
    public void readClient() { storeService.readClient(); }

    @ShellMethod(value = "Read receipts", key = {"r receipt"})
    public void readReceipt() { storeService.readReceipt(); }

    @ShellMethod(value = "Update product", key = {"u product"})
    public void updateProduct() {
        storeService.updateProduct();
    }

    @ShellMethod(value = "Update client", key = {"u client"})
    public void updateClient() {
        storeService.updateClient();
    }

    @ShellMethod(value = "Update receipt", key = {"u receipt"})
    public void updateReceipt() {
        storeService.updateReceipt();
    }

    @ShellMethod(value = "Delete product", key = {"d product"})
    public void deleteProduct() {
        storeService.deleteProduct();
    }

    @ShellMethod(value = "Delete client", key = {"d client"})
    public void deleteClient() {
        storeService.deleteClient();
    }

    @ShellMethod(value = "Delete receipt", key = {"d receipt"})
    public void deleteReceipt() {
        storeService.deleteReceipt();
    }

    @ShellMethod(value = "Open H2 WebUI ", key = {"web"})
    public void openH2WebUI(){
        if (!webUILaunched) {
            try {
                org.h2.tools.Console.main();
                webUILaunched = true;
                System.out.println("WebUI will be opened in your browser\n\n" +
                        "Copy that to clipboard:\n" +
                        "SELECT * FROM CLIENT;\n" +
                        "SELECT * FROM PRODUCT;\n" +
                        "SELECT * FROM RECEIPT;");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @ShellMethod(value = "Help", key = {"h", "help"})
    public void showCommands() {
        System.out.println(
                "Command list:\n\n" +
                "CREATE:\n" +
                "c product  -  create product\n" +
                "c client  -  create client\n" +
                "c receipt  - create receipt\n\n" +
                "READ:\n" +
                "r product  -  list all products\n" +
                "r client  -  list all clients\n" +
                "r receipt  - list all receipts\n\n" +
                "UPDATE:\n" +
                "u product  -  update product\n" +
                "u client  -  update client\n" +
                "u receipt  -  update receipt\n\n" +
                "DELETE:\n" +
                "d product  -  delete product\n" +
                "d client  -  delete client\n" +
                "d receipt  -  delete receipt\n"
        );
    }
}
