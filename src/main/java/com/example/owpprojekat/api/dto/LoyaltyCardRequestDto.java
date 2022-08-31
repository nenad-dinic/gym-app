package com.example.owpprojekat.api.dto;

import lombok.Getter;
import lombok.Setter;

public class LoyaltyCardRequestDto {

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private Long userId;

        public Get(Long id, Long userId) {
            this.id = id;
            this.userId = userId;
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
