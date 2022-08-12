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
    private String desc;
    private String pic;
    private int price;
    private boolean isGroup;
    private Difficulty difficulty;
    private int duration;
}
