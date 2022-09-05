package com.example.owpprojekat.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "wishlist")
public class Wishlist {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private Long userId;
    private Long trainingId;

    public Wishlist(Long userId, Long trainingId) {
        this.userId = userId;
        this.trainingId = trainingId;
    }

    public Wishlist() {
    }
}
