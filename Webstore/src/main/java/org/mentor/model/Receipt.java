package org.mentor.model;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Data
@Repository
@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "seq_receipt")
    @SequenceGenerator(name = "seq_receipt")
    private Integer id;
    private Integer bought;
    @Column(name = "total_price")
    private Double totalPrice;
    //Bi-direct(Owner side)
    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "client_id")
    private Client client;
    //Bi-direct(Owner side)
    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "product_id")
    private Product product;

    public Receipt(){}

    public Receipt(Integer bought, Double totalPrice) {
        this.bought = bought;
        this.totalPrice = totalPrice;
    }

    public Receipt(Integer id, Integer bought, Double totalPrice, Client client, Product product) {
        this.id = id;
        this.bought = bought;
        this.totalPrice = totalPrice;
        this.client = client;
        this.product = product;
    }
}
