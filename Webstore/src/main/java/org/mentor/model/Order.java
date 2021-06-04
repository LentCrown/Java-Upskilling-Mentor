package org.mentor.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "T_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "seq_order")
    @SequenceGenerator(name = "seq_order")
    @Column(name="id")
    private Integer id;
    @Column(name="order_time")
    private Timestamp orderTime;
    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinTable(name="T_Ordered_Products",
                joinColumns = {@JoinColumn(name="order_id")},
                inverseJoinColumns = {@JoinColumn(name="product_id")})
    private List<Product> products = new ArrayList<>();

    public Order(){}

    public Order(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @PrePersist
    @PreUpdate
    @Transient
    public void setLastEdited() {
        this.orderTime = new Timestamp(System.currentTimeMillis());
    }
}
