package org.mentor.repository;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "seq_order")
    @SequenceGenerator(name = "seq_order")
    private Integer id;
    private Integer howMuchBuying;
    private Double totalPrice;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "client_id")
    private Client client;

/*    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "product_id")
    private Product product;*/

    Receipt(){}

    public Receipt(Integer howMuchBuying, Double totalPrice) {
        this.howMuchBuying = howMuchBuying;
        this.totalPrice = totalPrice;
    }
}
