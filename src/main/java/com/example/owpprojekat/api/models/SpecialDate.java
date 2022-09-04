package com.example.owpprojekat.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "special_date")
public class SpecialDate {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String name;
    private LocalDateTime date;
    private int discount;

    public SpecialDate(String name, LocalDateTime date, int discount) {
        this.name = name;
        this.date = date;
        this.discount = discount;
    }

    public SpecialDate() {
    }
}
