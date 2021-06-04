package org.mentor.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "T_PRODUCT")
@Table(name = "T_PRODUCT")
public class Product{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "SEQ_PRODUCT")
    @SequenceGenerator(name = "SEQ_PRODUCT")
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DESCRIPTION")
    private String desc;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "STORED")
    private Integer stored;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="T_Ordered_Products",
            joinColumns = {@JoinColumn(name="PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name="ORDER_ID")})
    private List<Order> orders = new ArrayList<>();

    public Product() {}

    public Product(String desc, Double price, Integer stored) {
        this.desc = desc;
        this.price = price;
        this.stored = stored;
    }
}
