package com.example.owpprojekat.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "schedule")
public class Schedule {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private Long hallId;
    private Long trainingId;
    private LocalDateTime date;

    public Schedule(Long hallId, Long trainingId, LocalDateTime date) {
        this.hallId = hallId;
        this.trainingId = trainingId;
        this.date = date;
    }

    public Schedule() {
    }
}
