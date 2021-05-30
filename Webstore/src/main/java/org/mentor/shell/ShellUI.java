package org.mentor.shell;

import org.mentor.service.StoreService;

public class ShellUI implements StoreService {
    @Override
    public void registerNewProduct() {
        System.out.println("registerNewProduct");
    }

    @Override
    public void registerNewClient() {
        System.out.println("registerNewClient");
    }

    @Override
    public void registerNewOrder() {
        System.out.println("registerNewOrder");
    }

    @Override
    public void changeProductNumbers() {
        System.out.println("changeProductNumbers");
    }
}
