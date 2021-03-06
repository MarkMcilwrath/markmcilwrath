package com8.markmcilwrath.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

    private String uuid;
    private String firstname;
    private String lastname;
    private String email;
    private boolean admin;


    /**
     * NOTE: the json constructor ignores multiple fields!
     */
    public User(
            String uuid,
            String firstname,
            String lastname,
            String email,
            boolean admin
    ) {
        this.uuid = uuid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.admin = admin;
    }
}
