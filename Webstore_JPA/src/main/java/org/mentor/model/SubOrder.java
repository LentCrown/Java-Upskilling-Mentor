package org.mentor.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "T_SUBORDERS")
@Table(name = "T_SUBORDERS")
public class SubOrder implements Serializable {
    @Id
    @Column(name="PRODUCT_ID")
    private Integer product_id;
    @Id
    @Column(name="ORDER_ID")
    private Integer order_id;
    @Column(name="BOUGHT")
    private Integer bought;
    @Column(name="SUB_PRICE")
    private Double sub_price;

    @ManyToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name="PRODUCT_ID", referencedColumnName="ID")
    private Product product;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="ORDER_ID", referencedColumnName="ID")
    private Order order;

    public SubOrder() {}

    public SubOrder(Integer product_id, Integer order_id, Integer bought, Double sub_price) {
        this.product_id = product_id;
        this.order_id = order_id;
        this.bought = bought;
        this.sub_price = sub_price;
    }
}
