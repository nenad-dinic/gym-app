package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.ReservationDto;
import com.example.owpprojekat.api.models.*;
import com.example.owpprojekat.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    ReservationToScheduleRepo reservationToScheduleRepo;

    @Autowired
    ScheduleRepo scheduleRepo;

    @Autowired
    LoyaltyCardRepo cardRepo;

    @Autowired
    TrainingRepo trainingRepo;

    @Autowired
    SpecialDateRepo specialDateRepo;

    @PostMapping(value = "/api/reservation",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    ReservationDto.Get createReservation(@RequestBody ReservationDto.Add data) {
        List<Schedule> overLappingSchedules = scheduleRepo.getOverlappingSchedules(data.getSchedules(), data.getUserId());
        if (!overLappingSchedules.isEmpty()) {
            //TODO kad se isti rezervise ponovo dopusti, ispraiti to
            return null;
        }

        LocalDateTime today = LocalDateTime.now();
        SpecialDate date = specialDateRepo.isDateSpecial(today.getDayOfMonth(), today.getMonthValue(), today.getYear());


        try {
            LoyaltyCard card = cardRepo.findByUserId(data.getUserId());

            if (data.getPointsUsed() < 0 || data.getPointsUsed() > 5) {
                return null;
            }

            if (data.getPointsUsed() > 0) {
                if (date != null) {return null;}
                if (card != null) {
                    if (card.getPoints() < data.getPointsUsed()) {
                        return null;
                    }
                } else {
                    return null;
                }
            }

            Reservation r = reservationRepo.save(new Reservation(data.getUserId(), 0, LocalDateTime.now()));
            int saveCount = 0;
            int savePrice = 0;
            for (Long id : data.getSchedules()) {
                try {
                    if (scheduleRepo.isFree(id) > 0) {
                        reservationToScheduleRepo.save(new ReservationToSchedule(id, r.getId()));
                        saveCount++;
                        savePrice += trainingRepo.findById(scheduleRepo.findById(id).get().getTrainingId()).get().getPrice();
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            if (saveCount == 0) {
                reservationRepo.delete(r);
                return null;
            }

            try {
                if (card != null && data.getPointsUsed() > 0) {
                    double discount = data.getPointsUsed() * 0.05;
                    card.setPoints(card.getPoints() - data.getPointsUsed());
                    cardRepo.save(card);
                    savePrice *= (1 - discount);
                    System.out.println("im here 1");
                }
                if (card != null && data.getPointsUsed() == 0 && date == null) {
                    int pointsGained = savePrice / 500;
                    card.setPoints(card.getPoints() + pointsGained);
                    cardRepo.save(card);
                    System.out.println("im here 2");

                }
            } catch (Exception e) {
                //don't apply discount or add points
            }

            if (date != null) {
                savePrice *= (1 - (float)date.getDiscount() / 100);
            }

            r.setPrice(savePrice);
            reservationRepo.save(r);

            ReservationDto.Get result = new ReservationDto.Get(r.getUserId(), r.getPrice(), r.getDateTime());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
