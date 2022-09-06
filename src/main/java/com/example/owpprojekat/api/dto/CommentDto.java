package com.example.owpprojekat.api.dto;

import com.example.owpprojekat.api.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

public class CommentDto {

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private String text;
        private int rating;
        private LocalDateTime date;
        private String username;
        private String name;
        private Status status;
        private boolean isAnonymous;

        public Get(Long id, String text, int rating, LocalDateTime date, String username, String name, Status status, boolean isAnonymous) {
            this.id = id;
            this.text = text;
            this.rating = rating;
            this.date = date;
            this.username = username;
            this.name = name;
            this.status = status;
            this.isAnonymous = isAnonymous;
        }

        public Get() {
        }
    }

    @Getter
    @Setter
    public static class Add {
        private String text;
        private int rating;
        private Long userId;
        private Long trainingId;
        private boolean isAnonymous;

        public Add(String text, int rating, Long userId, Long trainingId, boolean isAnonymous) {
            this.text = text;
            this.rating = rating;
            this.userId = userId;
            this.trainingId = trainingId;
            this.isAnonymous = isAnonymous;
        }

        public Add() {
        }
    }
}
