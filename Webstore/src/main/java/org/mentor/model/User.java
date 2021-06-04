package org.mentor.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "T_USER")
@Table(name = "T_USER")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator = "SEQ_USER")
    @SequenceGenerator(name = "SEQ_USER")
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "PHONE_NUMBER")
    private String phone_number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CRED_ID")
    private Credential credential;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
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
