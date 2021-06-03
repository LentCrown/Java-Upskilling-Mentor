package org.mentor.domain;

import lombok.Data;

@Data
public class UserSession {
    private Integer id;
    private String name;
    private String surname;

    public UserSession(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
