package com8.markmcilwrath.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;


@Getter
@Setter
@ToString
public class User {

    private String uuid;

    @NotBlank(message = "First Name is mandatory")
    private String firstname;

    @NotBlank(message = "Surname is mandatory")
    private String lastname;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    private boolean admin;


    public User(
            String uuid,
            String firstname,
            String lastname,
            String email,
            boolean admin
    )
    {
        this.uuid = uuid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.admin = admin;
    }
}
