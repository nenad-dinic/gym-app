package com.example.owpprojekat.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "reservation")
public class Reservation {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private Long userId;
    private int price;
    private LocalDateTime dateTime;

    public Reservation(Long userId, int price, LocalDateTime dateTime) {
        this.userId = userId;
        this.price = price;
        this.dateTime = dateTime;
    }

    public Reservation() {
    }
}
