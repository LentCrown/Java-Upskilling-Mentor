package org.mentor.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "seq_client")
    @SequenceGenerator(name = "seq_client")
    private Integer id;
    private String name;
    private String surname;
    private String phone_number;
    //Bi-direct
    @OneToMany(mappedBy = "client")
    private List<Receipt> receipts;

    public Client() {}

    public Client(String name, String surname, String phone_number) {
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
    }
}
