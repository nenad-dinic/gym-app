package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfilePageController {

    RestTemplate client = new RestTemplate();

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/profile")
    public String profile(Model model) {
        Long id = Long.parseLong(request.getParameter("id"));
        UserDto.Get user = client.getForObject("http://localhost:8080/api/user?id=" + id, UserDto.Get.class);
        model.addAttribute("data", user);
        return "profile";
    }
}
