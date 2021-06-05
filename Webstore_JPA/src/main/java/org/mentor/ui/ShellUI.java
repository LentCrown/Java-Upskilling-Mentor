package org.mentor.ui;

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

    @ShellMethod(value = "Create product", key = {"product -c"})
    public void createProduct() {

    }

    @ShellMethod(value = "Create order", key = {"order", "order -c"})
    public void createOrder() {

    }

    @ShellMethod(value = "Show products", key = {"prices"})
    public void showProducts() {

    }

    @ShellMethod(value = "Show order history", key = {"order history", "order -h"})
    public void showPurchaseHistory() {

    }

    @ShellMethod(value = "Delete product", key = {"product -d"})
    public void deleteProduct() {

    }

    @ShellMethod(value = "Delete order", key = {"order -d"})
    public void deleteOrder() {

    }

    @ShellMethod(value = "Open H2 WebUI ", key = {"web"})
    public void openH2WebUI(){
        if (!webUILaunched) {
            try {
                org.h2.tools.Console.main();
                webUILaunched = true;
                System.out.println("\nWebUI will be opened in your browser\n" +
                        "Copy that to clipboard:\n\n" +
                        "SELECT * FROM T_USER;\n" +
                        "SELECT * FROM T_ORDERED_PRODUCTS;\n" +
                        "SELECT * FROM T_ORDER;\n" +
                        "SELECT * FROM T_PRODUCT;\n\n");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
