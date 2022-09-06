package com.example.owpprojekat.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ScheduleDto {

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private String hallTag;
        private String trainingName;
        private LocalDateTime date;

        public Get(Long id, String hallTag, String trainingName, LocalDateTime date) {
            this.id = id;
            this.hallTag = hallTag;
            this.trainingName = trainingName;
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
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime date;
    }
}
