package com.example.owpprojekat.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ScheduleDto {

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private String hallTag;
        private Long trainingId;
        private LocalDateTime date;

        public Get(Long id, String hallTag, Long trainingId, LocalDateTime date) {
            this.id = id;
            this.hallTag = hallTag;
            this.trainingId = trainingId;
            this.date = date;
        }

        public Get() {
        }
    }

    @Setter
    @Getter
    public static class Add {
        private Long hallId;
        private Long trainingId;
        private LocalDateTime date;
    }
}
