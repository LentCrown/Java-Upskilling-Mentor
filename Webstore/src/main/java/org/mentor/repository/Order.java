package org.mentor.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer id_merch;
    private Integer id_client;
    private Integer howMuchBuying;
    private Double totalPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_merch() {
        return id_merch;
    }

    public void setId_merch(Integer id_merch) {
        this.id_merch = id_merch;
    }

    public Integer getId_client() {
        return id_client;
    }

    public void setId_client(Integer id_client) {
        this.id_client = id_client;
    }

    public Integer getHowMuchBuying() {
        return howMuchBuying;
    }

    public void setHowMuchBuying(Integer howMuchBuying) {
        this.howMuchBuying = howMuchBuying;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
