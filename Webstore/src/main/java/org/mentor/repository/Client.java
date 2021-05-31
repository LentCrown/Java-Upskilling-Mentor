package org.mentor.repository;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "seq_client")
    @SequenceGenerator(name = "seq_client")
    private Long id;
    private String name;
    private String surname;
    private String phone_number;

    @OneToOne(mappedBy = "client")
    private Order order;
}
