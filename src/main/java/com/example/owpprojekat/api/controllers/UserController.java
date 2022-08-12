package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.models.User;
import com.example.owpprojekat.api.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
