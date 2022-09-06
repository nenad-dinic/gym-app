package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.ScheduleDto;
import com.example.owpprojekat.api.models.Schedule;
import com.example.owpprojekat.api.repositories.HallRepo;
import com.example.owpprojekat.api.repositories.ScheduleRepo;
import com.example.owpprojekat.api.repositories.TrainingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    ScheduleRepo scheduleRepo;

    @Autowired
    HallRepo hallRepo;

    @Autowired
    TrainingRepo trainingRepo;

    @GetMapping(value = "/api/schedule/training",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<ScheduleDto.Get> getSchedules(@RequestParam("id") String id) {
        try {
            List<ScheduleDto.Get> result = new ArrayList<>();
            List<Schedule> schedules = scheduleRepo.getSchedulesForTraining(Long.parseLong(id));
            for (Schedule s : schedules) {
                result.add(new ScheduleDto.Get(s.getId(), hallRepo.findById(s.getHallId()).get().getTag(), trainingRepo.findById(s.getTrainingId()).get().getName(), s.getDate()));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(value = "/api/schedule",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    ScheduleDto.Get postSchedule(@RequestBody ScheduleDto.Add data) {
        try {
            List<Schedule> overlappingSchedule = scheduleRepo.getOverlappingSchedulesForHall(data.getHallId(), data.getDate(), trainingRepo.findById(data.getTrainingId()).get().getDuration());
            if (!overlappingSchedule.isEmpty()) {
                throw new IOException("New schedule overlaps existing ones");
            }
            Schedule s = scheduleRepo.save(new Schedule(data.getHallId(), data.getTrainingId(), data.getDate()));
            ScheduleDto.Get result = new ScheduleDto.Get(s.getId(), hallRepo.findById(s.getHallId()).get().getTag(), trainingRepo.findById(s.getTrainingId()).get().getName(), s.getDate());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "/api/schedule",
    produces = MediaType.APPLICATION_JSON_VALUE)
    ScheduleDto.Get getSchedule(@RequestParam("id") String id) {
        try {
            Schedule s = scheduleRepo.findById(Long.parseLong(id)).get();
            ScheduleDto.Get result = new ScheduleDto.Get(s.getId(), hallRepo.findById(s.getHallId()).get().getTag(), trainingRepo.findById(s.getTrainingId()).get().getName(), s.getDate());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "/api/schedule/reservation",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<ScheduleDto.Get> getSchedulesForReservation(@RequestParam("id") String id) {
        try {
            List<Schedule> schedules = scheduleRepo.getSchedulesForReservation(Long.parseLong(id));
            List<ScheduleDto.Get> result = new ArrayList<>();
            for (Schedule s : schedules) {
                result.add(new ScheduleDto.Get(s.getId(), hallRepo.findById(s.getHallId()).get().getTag(), trainingRepo.findById(s.getTrainingId()).get().getName(), s.getDate()));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
