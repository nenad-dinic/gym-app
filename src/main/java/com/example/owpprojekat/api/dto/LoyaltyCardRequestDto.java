package com.example.owpprojekat.api.dto;

import lombok.Getter;
import lombok.Setter;

public class LoyaltyCardRequestDto {

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private String username;

        public Get(Long id, String username) {
            this.id = id;
            this.username = username;
        }

        public Get() {
        }
    }

    @Getter
    @Setter
    public static class Add {
        private Long userId;

        public Add(Long userId) {
            this.userId = userId;
        }

        public Add() {
        }
    }
}
