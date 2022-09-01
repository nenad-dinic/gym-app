package com.example.owpprojekat.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "loyalty_card")
public class LoyaltyCard {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private Long userId;
    private int points;


    public LoyaltyCard(Long userId, int points) {
        this.userId = userId;
        this.points = points;

    }

    public LoyaltyCard() {
    }
}
