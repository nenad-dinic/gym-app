package com.example.owpprojekat.api.dto;

import com.example.owpprojekat.api.enums.Difficulty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class TrainingDto {

    @Getter
    @Setter
    public static class Get {
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
        private List<String> types;

        public Get(Long id, String name, String trainers, String description, String pic, int price, boolean isGroup, Difficulty difficulty, int duration, float rating, List<String> types) {
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
            this.types = types;
        }

        public Get() {
        }
    }

    @Setter
    @Getter
    public static class Add {
        private String name;
        private String trainers;
        private String description;
        private String pic;
        private int price;
        private boolean isGroup;
        private Difficulty difficulty;
        private int duration;
        private List<Long> types;
    }

    @Setter
    @Getter
    public static class Update {
        private String name;
        private String trainers;
        private String description;
        private String pic;
        private int price;
        private boolean isGroup;
        private Difficulty difficulty;
        private int duration;
        private List<Long> types;

    }
}
