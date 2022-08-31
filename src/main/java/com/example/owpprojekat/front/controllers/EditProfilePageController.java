package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.UserDto;
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
public class EditProfilePageController {

    RestTemplate client = new RestTemplate();

    @Autowired
    private HttpServletRequest request;

    private Long id;

    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        UserDto.Get user;
        try {
            id = Long.parseLong(request.getParameter("id"));
            user = client.getForObject("http://localhost:8080/api/user?id=" + id, UserDto.Get.class);
            if (user == null) {
                return "redirect:/profile?id=" + id;
            }
        } catch (Exception e) {
            user = null;
            id = null;
        }

        UserDto.Update data = new UserDto.Update();
        if (user != null) {
            data.setUsername(user.getUsername());
            data.setEmail(user.getEmail());
            data.setName(user.getName());
            data.setLastname(user.getLastname());
            data.setDateOfBirth(user.getDateOfBirth());
            data.setAddress(user.getAddress());
            data.setPhoneNum(user.getPhoneNum());
            data.setOldPassword("");
            data.setPassword("");
        } else {
            return "redirect:/profile?id=" + id;
        }

        model.addAttribute("data", data);
        return "editProfile";
    }

    @PostMapping("/profile/edit")
    public String postEditProfile(@ModelAttribute UserDto.Update data, Model model) {
        model.addAttribute("data", data);
        ResponseEntity<UserDto.Get> response;
        response = client.exchange("http://localhost:8080/api/user?id=" + id, HttpMethod.PUT, new HttpEntity<>(data), UserDto.Get.class);
        if (!response.hasBody()) {
            return "redirect:/profile/edit?id=" + id;
        }
        return "redirect:/profile?id=" + id;
    }
}
