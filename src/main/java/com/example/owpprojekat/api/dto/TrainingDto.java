package com.example.owpprojekat.api.dto;

import com.example.owpprojekat.api.enums.Difficulty;
import lombok.Getter;
import lombok.Setter;

public class TrainingDto {

    @Getter
    @Setter
    public static class Get{
        private Long id;
        private String name;
        private String trainers;
        private String description;
        private String pic;
        private int price;
        private boolean isGroup;
        private Difficulty difficulty;
        private int duration;
        private float rating;
        private String type;

        public Get(Long id, String name, String trainers, String description, String pic, int price, boolean isGroup, Difficulty difficulty, int duration, float rating, String type) {
            this.id = id;
            this.name = name;
            this.trainers = trainers;
            this.description = description;
            this.pic = pic;
            this.price = price;
            this.isGroup = isGroup;
            this.difficulty = difficulty;
            this.duration = duration;
            this.rating = rating;
            this.type = type;
        }

        public Get() {
        }
    }

    @Setter
    @Getter
    public static class Add{
        private String name;
        private String trainers;
        private String description;
        private String pic;
        private int price;
        private boolean isGroup;
        private Difficulty difficulty;
        private int duration;


    }
}
