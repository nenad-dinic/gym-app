package com.example.owpprojekat.api.dto;

import lombok.Getter;
import lombok.Setter;

public class TrainingTypeDto {

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private String name;
        private String description;

        public Get(Long id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        public Get() {
        }
    }
}
