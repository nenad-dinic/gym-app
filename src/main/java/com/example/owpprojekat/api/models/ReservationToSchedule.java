package com.example.owpprojekat.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "reservationToSchedule")
public class ReservationToSchedule {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private Long scheduleId;
    private Long reservationId;

    public ReservationToSchedule(Long scheduleId, Long reservationId) {
        this.scheduleId = scheduleId;
        this.reservationId = reservationId;
    }

    public ReservationToSchedule() {
    }
}
