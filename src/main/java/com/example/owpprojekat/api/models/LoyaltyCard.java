package com.example.owpprojekat.api.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class LoyaltyCard {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String discount;
    private int points;
    private Long userId;
}
