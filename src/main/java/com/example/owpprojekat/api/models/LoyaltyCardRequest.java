package com.example.owpprojekat.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "loyalty_card_request")
public class LoyaltyCardRequest {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private Long userId;

    public LoyaltyCardRequest(Long userId) {
        this.userId = userId;
    }

    public LoyaltyCardRequest() {
    }
}
