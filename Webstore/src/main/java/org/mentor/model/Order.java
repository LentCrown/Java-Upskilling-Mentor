package org.mentor.model;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Data
@Entity
@Table(name = "T_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "seq_order")
    @SequenceGenerator(name = "seq_order")
    private Integer id;
    private Integer bought;
    @Column(name = "total_price")
    private Double totalPrice;
    //Bi-direct(Owner side)
    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "user_id")
    private User user;
    //Bi-direct(Owner side)
    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "product_id")
    private Product product;

    public Order(){}

    public Order(Integer bought, Double totalPrice) {
        this.bought = bought;
        this.totalPrice = totalPrice;
    }

    public Order(Integer id, Integer bought, Double totalPrice, User user, Product product) {
        this.id = id;
        this.bought = bought;
        this.totalPrice = totalPrice;
        this.user = user;
        this.product = product;
    }
}
