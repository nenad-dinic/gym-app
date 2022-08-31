package com.example.owpprojekat.api.dto;

import lombok.Getter;
import lombok.Setter;

public class LoyaltyCardDto {

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private int points;
        private Long userId;

        public Get(Long id, int points, Long userId) {
            this.id = id;
            this.points = points;
            this.userId = userId;
        }

        public Get() {
        }
    }

    @Getter
    @Setter
    public static class Add {
        private Long userId;
    }
}
