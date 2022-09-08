package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.HallDto;
import com.example.owpprojekat.api.dto.ScheduleDto;
import com.example.owpprojekat.api.dto.TrainingDto;
import com.example.owpprojekat.api.dto.UserDto;
import com.example.owpprojekat.api.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ModifySchedulePageController {

    RestTemplate client = new RestTemplate();

    @GetMapping("/schedule")
    public String schedule(Model model, HttpSession session) {
        try {
            UserDto.Get user = (UserDto.Get)session.getAttribute("user");
            if (user.getRole() != Role.ADMIN) {
                return "redirect:/";
            }
        } catch (Exception e) {
            return "redirect:/";
        }

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
        if (response == null) {
            return "redirect:/schedule";
            //TODO obavestiti korisnika o uspelim/neuspleim dodavanjima schedula
        }
        return "redirect:/admin";
    }
}
