package com.example.owpprojekat.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationDto {

    @Getter
    @Setter
    public static class Add {
        private Long userId;
        private int price;
        private List<Long> schedules;
    }

    @Getter
    @Setter
    public static class Get {
        private Long userId;
        private int price;
        private LocalDateTime dateTime;

        public Get(Long userId, int price, LocalDateTime dateTime) {
            this.userId = userId;
            this.price = price;
            this.dateTime = dateTime;
        }

        public Get() {
        }
    }
}
