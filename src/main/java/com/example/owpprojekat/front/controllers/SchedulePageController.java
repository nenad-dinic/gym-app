package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.HallDto;
import com.example.owpprojekat.api.dto.ScheduleDto;
import com.example.owpprojekat.api.dto.TrainingDto;
import com.example.owpprojekat.api.models.Schedule;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SchedulePageController {

    RestTemplate client = new RestTemplate();

    @GetMapping("/schedule")
    public String schedule(Model model) {
        List<TrainingDto.Get> trainings = new ArrayList<>();
        trainings = client.getForObject("http://localhost:8080/api/trainings", trainings.getClass());
        model.addAttribute("trainings", trainings);

        List<HallDto.Get> halls = new ArrayList<>();
        halls = client.getForObject("http://localhost:8080/api/halls", halls.getClass());
        model.addAttribute("halls" , halls);

        model.addAttribute("data", new ScheduleDto.Add());
        return "modifySchedule";
    }

    @PostMapping("/schedule")
    public String postSchedule(@ModelAttribute ScheduleDto.Add data, Model model) {
        model.addAttribute("data", data);
        ScheduleDto.Get response = client.postForObject("http://localhost:8080/api/schedule", data, ScheduleDto.Get.class);
        return "redirect:/admin";
    }
}
