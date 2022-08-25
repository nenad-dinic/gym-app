package com.example.owpprojekat.api.dto;

import lombok.Getter;
import lombok.Setter;

public class HallDto {

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private String hallTag;
        private int capacity;

        public Get(Long id, String hallTag, int capacity) {
            this.id = id;
            this.hallTag = hallTag;
            this.capacity = capacity;
        }

        public Get() {
        }
    }
}
