package com.example.owpprojekat.api.dto;

import com.example.owpprojekat.api.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class CommentDto {

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private String text;
        private int rating;
        private Date date;
        private String username;
        private Long trainingId;
        private Status status;
        private boolean isAnonymous;

        public Get(Long id, String text, int rating, Date date, String username, Long trainingId, Status status, boolean isAnonymous) {
            this.id = id;
            this.text = text;
            this.rating = rating;
            this.date = date;
            this.username = username;
            this.trainingId = trainingId;
            this.status = status;
            this.isAnonymous = isAnonymous;
        }

        public Get() {
        }
    }
}
