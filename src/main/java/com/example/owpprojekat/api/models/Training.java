package com.example.owpprojekat.api.models;

import com.example.owpprojekat.api.enums.Difficulty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "training")
public class Training {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String name;
    private String trainers;
    private String description;
    private String pic;
    private int price;
    private boolean isGroup;
    private Difficulty difficulty;
    private int duration;

    public Training(String name, String trainers, String description, String pic, int price, boolean isGroup, Difficulty difficulty, int duration) {
        this.name = name;
        this.trainers = trainers;
        this.description = description;
        this.pic = pic;
        this.price = price;
        this.isGroup = isGroup;
        this.difficulty = difficulty;
        this.duration = duration;
    }

    public Training() {
    }
}
