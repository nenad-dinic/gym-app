package com.example.owpprojekat.api.models;

import com.example.owpprojekat.api.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String text;
    private int rating;
    private LocalDateTime date;
    private Long userId;
    private Long trainingId;
    private Status status;
    private boolean isAnonymous;

    public Comment(String text, int rating, LocalDateTime date, Long userId, Long trainingId, Status status, boolean isAnonymous) {
        this.text = text;
        this.rating = rating;
        this.date = date;
        this.userId = userId;
        this.trainingId = trainingId;
        this.status = status;
        this.isAnonymous = isAnonymous;
    }

    public Comment() {
    }
}
