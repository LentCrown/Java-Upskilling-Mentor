package org.mentor.repository;

import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="T_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "seq_order")
    @SequenceGenerator(name = "seq_order")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "AMOUNT", nullable = false)
    private Integer howMuchBuying;
    @Column(name = "PRICE", nullable = false)
    private Double totalPrice;

    @OneToOne
    @JoinColumn(name="PRODUCT_ID", unique=true, nullable=false)
    private Product product;

    @OneToOne
    @JoinColumn(name="CLIENT_ID", unique=true, nullable=false)
    private Client client;
}
