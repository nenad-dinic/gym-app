package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.UserDto;
import com.example.owpprojekat.api.enums.Role;
import com.example.owpprojekat.api.exceptions.ErrorResponse;
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
    UserDto.Get getUser(@RequestParam("id") String id) {
        try {
            User u = userRepo.findById(Long.parseLong(id)).get();
            UserDto.Get result = new UserDto.Get(u.getId(), u.getUsername(), u.getEmail(), u.getName(), u.getLastname(), u.getDateOfBirth(), u.getAddress(), u.getPhoneNum(), u.getRegDateTime(), u.getRole(), u.isBlocked());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(value = "/api/user",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDto.Get register(@RequestBody UserDto.Add data) {
        try {
            User u = userRepo.save(new User(data.getUsername(), data.getPassword(), data.getEmail(), data.getName(), data.getLastname(), data.getDateOfBirth(), data.getAddress(), data.getPhoneNum(), LocalDateTime.now(), Role.MEMBER, false));
            UserDto.Get result = new UserDto.Get(u.getId(), u.getUsername(), u.getEmail(), u.getName(), u.getLastname(), u.getDateOfBirth(), u.getAddress(), u.getPhoneNum(), u.getRegDateTime(), u.getRole(), u.isBlocked());
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
            UserDto.Get result = new UserDto.Get(u.getId(), u.getUsername(), u.getEmail(), u.getName(), u.getLastname(), u.getDateOfBirth(), u.getAddress(), u.getPhoneNum(), u.getRegDateTime(), u.getRole(), u.isBlocked());
            if (u.isBlocked()) {
                return null;
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping(value = "api/user",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDto.Get editUser(@RequestParam("id") String id, @RequestBody UserDto.Update data) {
        try {
            User u = userRepo.findById(Long.parseLong(id)).get();
            u.setUsername(data.getUsername());
            u.setEmail(data.getEmail());
            u.setName(data.getName());
            u.setLastname(data.getLastname());
            u.setDateOfBirth(data.getDateOfBirth());
            u.setAddress(data.getAddress());
            u.setPhoneNum(data.getPhoneNum());
            if (data.getPassword().length() > 0) {
                if (!u.getPassword().equals(data.getOldPassword())) {
                    return null;
                }
                u.setPassword(data.getPassword());
            }

            userRepo.save(u);
            UserDto.Get result = new UserDto.Get(u.getId(), u.getUsername(), u.getEmail(), u.getName(), u.getLastname(), u.getDateOfBirth(), u.getAddress(), u.getPhoneNum(), u.getRegDateTime(), u.getRole(), u.isBlocked());

            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
