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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;
    @Column(name="ORDER_TIME")
    private Timestamp orderTime;
    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy="order", fetch = FetchType.EAGER)
    private List<SubOrder> orderedProducts;

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

    public void addProduct(Product product, Integer bought, Double sub_price) {
        SubOrder orderedProduct = new SubOrder();
        orderedProduct.setOrder(this);
        orderedProduct.setProduct(product);
        orderedProduct.setOrder_id(this.getId());
        orderedProduct.setProduct_id(product.getId());
        orderedProduct.setBought(bought);
        orderedProduct.setSub_price(sub_price);

        if(this.orderedProducts == null)
            this.orderedProducts = new ArrayList<>();

        this.orderedProducts.add(orderedProduct);
        // Also add the association object to the employee.
        product.getOrderedProducts().add(orderedProduct);
    }

    @Override
    public String toString() {
        return id + "   " + orderTime + "   " + totalPrice;
    }
}
