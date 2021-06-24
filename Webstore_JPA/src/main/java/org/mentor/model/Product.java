package org.mentor.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "T_PRODUCT")
@Table(name = "T_PRODUCT")
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DESCRIPTION")
    private String desc;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "STORED")
    private Integer stored;

    @OneToMany(mappedBy="product")
    private List<SubOrder> orderedProducts;

    public Product() {}

    public Product(String desc, Double price, Integer stored) {
        this.desc = desc;
        this.price = price;
        this.stored = stored;
    }

    @Override
    public String toString() {
        return id + "   " + desc + "   " + price + "   " + stored;
    }
}
