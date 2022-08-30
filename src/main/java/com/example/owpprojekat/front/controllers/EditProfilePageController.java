package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        } catch (Exception e) {
            user = null;
            id = null;
        }
        //TODO napravi userDto.Update i zameni
        //TODO posalji id korisnika kad se stisne dugme edit
        UserDto.Add data = new UserDto.Add();
        if (user != null) {
            data.setUsername(user.getUsername());
            data.setEmail(user.getEmail());
            data.setName(user.getName());
            data.setLastname(user.getLastname());
            data.setDateOfBirth(user.getDateOfBirth());
            data.setAddress(user.getAddress());
            data.setPhoneNum(user.getPhoneNum());
            data.setPassword("");
        }

        model.addAttribute("data", data);
        return "editProfile";
    }
}
