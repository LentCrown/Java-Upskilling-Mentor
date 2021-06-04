package org.mentor.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "T_CREDENTIALS")
@Table(name = "T_CREDENTIALS")
public class Credential {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator = "seq_credential")
    @SequenceGenerator(name = "seq_credential")
    @Column(name = "ID")
    private Integer id;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "IS_ADMIN")
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
