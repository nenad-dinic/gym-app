package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.ScheduleDto;
import com.example.owpprojekat.api.models.Schedule;
import com.example.owpprojekat.api.repositories.HallRepo;
import com.example.owpprojekat.api.repositories.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    ScheduleRepo scheduleRepo;

    @Autowired
    HallRepo hallRepo;

    @GetMapping(value = "/api/schedule/training",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<ScheduleDto.Get> getSchedules(@RequestParam("id") String id) {
        List<ScheduleDto.Get> result = new ArrayList<>();
        List<Schedule> schedules = scheduleRepo.getSchedulesForTraining(Long.parseLong(id));
        for (Schedule s : schedules) {
            result.add (new ScheduleDto.Get(s.getId(), hallRepo.findById(s.getHallId()).get().getTag(), s.getTrainingId(), s.getDate()));
        }
        return result;
    }
}
