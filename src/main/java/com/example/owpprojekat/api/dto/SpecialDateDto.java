package com.example.owpprojekat.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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

    @Getter
    @Setter
    public static class Add {
        private String name;
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate date;
        private int discount;

        public Add(String name, LocalDate date, int discount) {
            this.name = name;
            this.date = date;
            this.discount = discount;
        }

        public Add() {
        }
    }
}
