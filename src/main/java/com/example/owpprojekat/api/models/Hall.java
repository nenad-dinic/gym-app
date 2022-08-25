package com.example.owpprojekat.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "hall")
public class Hall {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String tag;
    private int capacity;

    public Hall(String tag, int capacity) {
        this.tag = tag;
        this.capacity = capacity;
    }

    public Hall() {
    }
}
