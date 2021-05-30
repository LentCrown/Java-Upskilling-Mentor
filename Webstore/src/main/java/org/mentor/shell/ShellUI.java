package org.mentor.shell;

import org.mentor.service.StoreService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.sql.SQLException;

@ShellComponent
public class ShellUI implements StoreService {
    private final StoreService storeService;

    public ShellUI(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    @ShellMethod(value = "Register new product", key = {"rnp"})
    public void registerNewProduct() {
        storeService.registerNewProduct();
    }

    @Override
    @ShellMethod(value = "Register new client", key = {"rnc"})
    public void registerNewClient() {
        storeService.registerNewClient();
    }

    @Override
    @ShellMethod(value = "Register new order ", key = {"rno"})
    public void registerNewOrder() {
        storeService.registerNewOrder();
    }

    @Override
    @ShellMethod(value = "Change product numbers ", key = {"cpn"})
    public void changeProductNumbers() {
        System.out.println("changeProductNumbers");
    }

    @ShellMethod(value = "Open H2 WebUI ", key = {"web"})
    public void openH2WebUI(){
        try {
            org.h2.tools.Console.main(new String[]{});
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
