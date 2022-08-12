package com.example.owpprojekat.api.models;

import com.example.owpprojekat.api.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDate dateOfBirth;
    private String address;
    private String phoneNum;
    private LocalDateTime regDateTime;
    private Role role;
}
