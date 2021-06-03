package org.mentor.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    @ManyToMany(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinTable(name="T_Ordered_Products",
                joinColumns = {@JoinColumn(name="order_id")},
                inverseJoinColumns = {@JoinColumn(name="product_id")})
    private List<Product> products;

    public Order(){}

    public Order(Integer bought, Double totalPrice) {
        this.bought = bought;
        this.totalPrice = totalPrice;
    }
}
