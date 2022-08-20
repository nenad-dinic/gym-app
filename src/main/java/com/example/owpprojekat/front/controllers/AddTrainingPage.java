package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.TrainingDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class AddTrainingPage {

    RestTemplate client = new RestTemplate();

    @GetMapping("/training/add")
    public String addTraining(Model model) {
        model.addAttribute("data", new TrainingDto.Add());
        return "addTraining";
    }

    @PostMapping("/training/add")
    public String postAddTraining(@ModelAttribute TrainingDto.Add data, Model model) {
        model.addAttribute("data", data);
        TrainingDto.Get response = client.postForObject("http://localhost:8080/api/training", data, TrainingDto.Get.class);
        if (response == null) {
            return "redirect:/";
        }
        return "redirect:/";
    }
}
