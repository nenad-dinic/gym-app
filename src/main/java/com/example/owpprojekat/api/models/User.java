package com.example.owpprojekat.api.models;

import com.example.owpprojekat.api.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastname;
    private Date dateOfBirth;
    private String address;
    private String phoneNum;
    private LocalDateTime regDateTime;
    private Role role;

    public User(String username, String password, String email, String name, String lastname, Date dateOfBirth, String address, String phoneNum, LocalDateTime regDateTime, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNum = phoneNum;
        this.regDateTime = LocalDateTime.now();
        this.role = role;
    }

    public User() {
    }
}
