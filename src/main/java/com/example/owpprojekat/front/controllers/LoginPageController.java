package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class LoginPageController {

    RestTemplate client = new RestTemplate();

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("data", new UserDto.Login());
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute UserDto.Login data, Model model) {
        model.addAttribute("data", data);
        UserDto.Get response;
        response = client.postForObject("http://localhost:8080/api/user/login", data, UserDto.Get.class);
        if (response == null) {
            return "redirect:/login";
        }
        return "redirect:/";
    }

}
