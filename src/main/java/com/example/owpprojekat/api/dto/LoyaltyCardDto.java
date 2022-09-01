package com.example.owpprojekat.api.dto;

import lombok.Getter;
import lombok.Setter;

public class LoyaltyCardDto {

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private Long userId;
        private int points;


        public Get(Long id, Long userId, int points) {
            this.id = id;
            this.userId = userId;
            this.points = points;

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
