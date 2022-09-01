package com.example.owpprojekat.api.models;

import com.example.owpprojekat.api.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime dateOfBirth;
    private String address;
    private String phoneNum;
    private LocalDateTime regDateTime;
    private Role role;
    private boolean isBlocked;

    public User(String username, String password, String email, String name, String lastname, LocalDateTime dateOfBirth, String address, String phoneNum, LocalDateTime regDateTime, Role role, boolean isBlocked) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNum = phoneNum;
        this.regDateTime = regDateTime;
        this.role = role;
        this.isBlocked = isBlocked;
    }

    public User() {
    }
}
