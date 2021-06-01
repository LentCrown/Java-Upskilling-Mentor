package org.mentor.model;

import lombok.Data;

import javax.persistence.*;

@Data
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

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "client_id")
    private Client client;

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
}
