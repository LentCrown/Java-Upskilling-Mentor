package org.mentor.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "T_CREDENTIALS")
public class Credential {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator = "seq_credential")
    @SequenceGenerator(name = "seq_credential")
    @Column(name = "id")
    private Integer id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "is_admin")
    private Boolean is_admin;

    @OneToOne(mappedBy = "credential", cascade = CascadeType.ALL)
    private User user;

    public Credential() {}
    public Credential(String login, String password, Boolean is_admin) {
        this.login = login;
        this.password = password;
        this.is_admin = is_admin;
    }
}
