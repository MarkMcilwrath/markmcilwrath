package com8.markmcilwrath.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;



@Entity
@Table(name = "user")
@EqualsAndHashCode()
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "internal_id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;


    @Getter
    @Setter
    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Getter
    @Setter
    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Getter
    @Setter
    @Column(name = "email", nullable = false)
    private String email;


    @Getter
    @Setter
    @Column(name = "admin", nullable = false)
    private boolean admin;


    public UserEntity(String userId, String firstName, String lastName, String email, boolean admin) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.admin = admin;
    }

}
