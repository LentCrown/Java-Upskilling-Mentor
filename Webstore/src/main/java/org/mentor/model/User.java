package org.mentor.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "T_USER")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "seq_user")
    @SequenceGenerator(name = "seq_user")
    private Integer id;
    private String name;
    private String surname;
    private String phone_number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cred_id")
    private Credential credential;

    //Bi-direct
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User() {}

    public User(String name, String surname, String phone_number) {
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
    }

    public User(Integer id, String name, String surname, String phone_number, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.orders = orders;
    }
}
