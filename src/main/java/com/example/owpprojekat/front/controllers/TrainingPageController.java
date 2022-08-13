package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.ScheduleDto;
import com.example.owpprojekat.api.dto.TrainingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainingPageController {

    RestTemplate client = new RestTemplate();

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/training")
    public String training(Model model) {
        Long id = Long.valueOf(request.getParameter("id"));
        TrainingDto.Get training = client.getForObject("http://localhost:8080/api/training?id=" + id, TrainingDto.Get.class);
        List<ScheduleDto.Get> schedule = new ArrayList<>();
        schedule = client.getForObject("http://localhost:8080/api/schedule/training?id=" + id, schedule.getClass());
        model.addAttribute("schedule", schedule);
        model.addAttribute("data", training);
        return "training";
    }
}
