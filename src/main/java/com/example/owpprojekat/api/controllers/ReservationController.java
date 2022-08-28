package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.ReservationDto;
import com.example.owpprojekat.api.models.Reservation;
import com.example.owpprojekat.api.models.ReservationToSchedule;
import com.example.owpprojekat.api.repositories.ReservationRepo;
import com.example.owpprojekat.api.repositories.ReservationToScheduleRepo;
import com.example.owpprojekat.api.repositories.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ReservationController {

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    ReservationToScheduleRepo reservationToScheduleRepo;

    @Autowired
    ScheduleRepo scheduleRepo;

    @PostMapping(value = "/api/reservation",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    ReservationDto.Get createReservation(@RequestBody ReservationDto.Add data) {
        try {
            Reservation r = reservationRepo.save(new Reservation(data.getUserId(), data.getPrice(), LocalDateTime.now()));
            ReservationDto.Get result = new ReservationDto.Get(r.getUserId(), r.getPrice(), r.getDateTime());
            int saveCount = 0;
            for (Long id : data.getSchedules()) {
                try {
                    if (scheduleRepo.isFree(id) > 0) {
                        reservationToScheduleRepo.save(new ReservationToSchedule(id, r.getId()));
                        saveCount++;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            if (saveCount == 0) {
                reservationRepo.delete(r);
                return null;
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
