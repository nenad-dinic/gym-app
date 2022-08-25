package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class RegisterPageController {

    RestTemplate client = new RestTemplate();

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("data", new UserDto.Add());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute UserDto.Add data, Model model) {
        model.addAttribute("data", data);
        UserDto.Get response = client.postForObject("http://localhost:8080/api/user", data, UserDto.Get.class);
        if (response == null) {
            return "redirect:/register";
            //TODO obavestiti korisnika o uspelim/neuspleim registraciji
        }
        return "redirect:/login";
    }
}
