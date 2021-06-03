package org.mentor.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "T_PRODUCT")
public class Product{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "seq_product")
    @SequenceGenerator(name = "seq_product")
    private Integer id;
    private String desc;
    @Column(name = "price_for_piece")
    private Double price;
    private Integer stored;

    @ManyToMany
    @JoinTable(name="T_Ordered_Products",
            joinColumns = {@JoinColumn(name="product_id")},
            inverseJoinColumns = {@JoinColumn(name="order_id")})
    private List<Order> orders;

    public Product() {}

    public Product(String desc, Double price, Integer stored) {
        this.desc = desc;
        this.price = price;
        this.stored = stored;
    }
}
