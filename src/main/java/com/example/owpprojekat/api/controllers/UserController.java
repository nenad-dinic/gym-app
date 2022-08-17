package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.UserDto;
import com.example.owpprojekat.api.enums.Role;
import com.example.owpprojekat.api.models.User;
import com.example.owpprojekat.api.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class UserController {
    @Autowired
    UserRepo userRepo;

    @GetMapping(value = "/api/user",
    produces = MediaType.APPLICATION_JSON_VALUE)
    User getUser(@RequestParam("id") String id) {
        User u = userRepo.findById(Long.parseLong(id)).get();
        return u;
    }

    @PostMapping(value = "/api/user",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDto.Get register(@RequestBody UserDto.Add data) {
        try {
            User u = userRepo.save(new User(data.getUsername(), data.getPassword(), data.getEmail(), data.getName(), data.getLastname(), data.getDateOfBirth(), data.getAddress(), data.getPhoneNum(), LocalDateTime.now(), Role.MEMBER));
            UserDto.Get result = new UserDto.Get(u.getId(), u.getUsername(), u.getEmail(), u.getName(), u.getLastname(), u.getDateOfBirth(), u.getAddress(), u.getPhoneNum(), u.getRegDateTime(), u.getRole());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(value = "/api/user/login",
    produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto.Get getUser(@RequestBody UserDto.Login data) {
        try {
            User u = userRepo.findByUsernameAndPassword(data.getUsername(), data.getPassword());
            UserDto.Get result = new UserDto.Get(u.getId(), u.getUsername(), u.getEmail(), u.getName(), u.getLastname(), u.getDateOfBirth(), u.getAddress(), u.getPhoneNum(), u.getRegDateTime(), u.getRole());
            return result;
        } catch (Exception e) {
            return null;
        }

    }

}
