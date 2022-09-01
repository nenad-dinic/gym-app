package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.HallDto;
import com.example.owpprojekat.api.dto.LoyaltyCardRequestDto;
import com.example.owpprojekat.api.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminPageController {

    RestTemplate client = new RestTemplate();

    @GetMapping("/admin")
    public String admin(Model model) {
        List<HallDto.Get> halls = new ArrayList<>();
        halls = client.getForObject("http://localhost:8080/api/halls", halls.getClass());
        model.addAttribute("halls", halls);

        List<LoyaltyCardRequestDto.Get> requests = new ArrayList<>();
        requests = client.getForObject("http://localhost:8080/api/card/request", requests.getClass());
        model.addAttribute("requests", requests);

        List<UserDto.Get> users = new ArrayList<>();
        users = client.getForObject("http://localhost:8080/api/users", users.getClass());
        model.addAttribute("users", users);

        return "admin";
        //TODO ako nije admin redirect na home
    }
}
