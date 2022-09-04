package com.example.owpprojekat.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class SpecialDateDto {

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private String name;
        private LocalDateTime date;
        private int discount;

        public Get(Long id, String name, LocalDateTime date, int discount) {
            this.id = id;
            this.name = name;
            this.date = date;
            this.discount = discount;
        }

        public Get() {
        }
    }
}
