package org.mentor.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "T_ORDER")
@Table(name = "T_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "SEQ_ORDER")
    @SequenceGenerator(name = "SEQ_ORDER")
    @Column(name="ID")
    private Integer id;
    @Column(name="ORDER_TIME")
    private Timestamp orderTime;
    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToMany(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinTable(name="T_ORDERED_PRODUCTS",
                joinColumns = {@JoinColumn(name="ORDER_ID")},
                inverseJoinColumns = {@JoinColumn(name="PRODUCT_ID")})
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
