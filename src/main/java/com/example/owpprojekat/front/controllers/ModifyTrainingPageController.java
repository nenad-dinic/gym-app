package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.TrainingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ModifyTrainingPageController {

    RestTemplate client = new RestTemplate();

    @Autowired
    private HttpServletRequest request;

    private Long id;

    @GetMapping("/training/modify")
    public String addTraining(Model model) {
        TrainingDto.Get training;
        try {
            id = Long.valueOf(request.getParameter("id"));
            training = client.getForObject("http://localhost:8080/api/training?id=" + id, TrainingDto.Get.class);
        } catch (Exception e) {
            training = null;
            id = null;
        }

        TrainingDto.Add data = new TrainingDto.Add();
        if (training != null) {
            data.setName(training.getName());
            data.setTrainers(training.getTrainers());
            data.setDescription(training.getDescription());
            data.setPic(training.getPic());
            data.setPrice(training.getPrice());
            data.setGroup(training.isGroup());
            data.setDifficulty(training.getDifficulty());
            data.setDuration(training.getDuration());
        } else {
            data.setPrice(100);
            data.setDuration(30);
        }
        model.addAttribute("data", data);
        return "modifyTraining";
    }

    @PostMapping("/training/modify")
    public String postAddTraining(@ModelAttribute TrainingDto.Add data, Model model) {
        model.addAttribute("data", data);
        ResponseEntity<TrainingDto.Get> response;
        if (id != null) {
            response = client.exchange("http://localhost:8080/api/training?id=" + id, HttpMethod.PUT , new HttpEntity<>(data), TrainingDto.Get.class);
        } else {
            response = client.exchange("http://localhost:8080/api/training", HttpMethod.POST, new HttpEntity<>(data), TrainingDto.Get.class);
        }

        //TODO obavestiti korisnika o uspelim/neuspleim dodavanjima/izmenama
        if (response == null) {
            return "redirect:/";
        }
        return "redirect:/";
    }


}
