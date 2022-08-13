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
    private String discount;
    private int points;
    private Long userId;
}
