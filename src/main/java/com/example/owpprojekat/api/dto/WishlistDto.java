package com.example.owpprojekat.api.dto;

import lombok.Getter;
import lombok.Setter;

public class WishlistDto {

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private Long userId;
        private String name;

        public Get(Long id, Long userId, String name) {
            this.id = id;
            this.userId = userId;
            this.name = name;
        }

        public Get() {
        }
    }

    @Getter
    @Setter
    public static class Add {
        private Long userId;
        private Long trainingId;

        public Add(Long userId, Long trainingId) {
            this.userId = userId;
            this.trainingId = trainingId;
        }

        public Add() {
        }
    }

    @Getter
    @Setter
    public static class Delete {
        private Long id;

        public Delete(Long id) {
            this.id = id;
        }

        public Delete() {
        }
    }
}
