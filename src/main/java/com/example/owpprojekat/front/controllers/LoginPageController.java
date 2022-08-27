package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Controller
public class LoginPageController {

    RestTemplate client = new RestTemplate();

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("data", new UserDto.Login());
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute UserDto.Login data, Model model, HttpSession session) {
        model.addAttribute("data", data);
        UserDto.Get response;
        response = client.postForObject("http://localhost:8080/api/user/login", data, UserDto.Get.class);
        if (response == null) {
            return "redirect:/login";
            //TODO obavestiti korisnika o uspelim/neuspleim logovanju
        }
        session.setAttribute("user", response);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("user", null);
        return "redirect:/";
    }

}
