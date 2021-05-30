package org.mentor.service;

import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService{
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
