package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.TrainingDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePageController {

    RestTemplate client = new RestTemplate();

    @GetMapping("/")
    public String home(Model model) {
        List<TrainingDto.Get> trainings = new ArrayList<>();
        trainings = client.getForObject("http://localhost:8080/api/training/available", trainings.getClass());
        model.addAttribute("trainings", trainings);
        return "index";
    }

}
