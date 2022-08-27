package com.example.owpprojekat.api.dto;

import com.example.owpprojekat.api.models.TrainingType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class CartItemDto {

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private String name;
        private String trainers;
        private List<String> types;
        private LocalDateTime date;
        private int price;

        public Get(Long id, String name, String trainers, List<String> types, LocalDateTime date, int price) {
            this.id = id;
            this.name = name;
            this.trainers = trainers;
            this.types = types;
            this.date = date;
            this.price = price;
        }

        public Get() {
        }
    }

    @Getter
    @Setter
    public static class CartItems {
        private List<Long> items;

        public CartItems(List<Long> items) {
            this.items = items;
        }

        public CartItems() {
        }
    }


}
