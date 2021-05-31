package org.mentor.repository;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="PRODUCT")
public class Product{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "seq_product")
    @SequenceGenerator(name = "seq_product")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "DESC", nullable = false)
    private String desc;
    @Column(name = "PRICE", nullable = false)
    private Double price;
    @Column(name = "STORED", nullable = false)
    private Integer stored;

    @OneToOne(mappedBy = "product")
    private Order order;
}
