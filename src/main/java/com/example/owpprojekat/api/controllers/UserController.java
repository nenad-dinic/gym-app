package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.UserDto;
import com.example.owpprojekat.api.dto.WishlistDto;
import com.example.owpprojekat.api.enums.Role;
import com.example.owpprojekat.api.exceptions.ErrorResponse;
import com.example.owpprojekat.api.models.User;
import com.example.owpprojekat.api.models.Wishlist;
import com.example.owpprojekat.api.repositories.TrainingRepo;
import com.example.owpprojekat.api.repositories.UserRepo;
import com.example.owpprojekat.api.repositories.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.owpprojekat.api.enums.Role.ADMIN;

@RestController
public class UserController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    WishlistRepo wishlistRepo;

    @Autowired
    TrainingRepo trainingRepo;

    @GetMapping(value = "api/users",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto.Get> getUsers() {
        try {
            List<UserDto.Get> result = new ArrayList<>();
            List<User> users = userRepo.findAll();
            for (User u : users) {
                result.add(new UserDto.Get(u.getId(), u.getUsername(), u.getEmail(), u.getName(), u.getLastname(), u.getDateOfBirth(), u.getAddress(), u.getPhoneNum(), u.getRegDateTime(), u.getRole(), u.isBlocked()));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

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

    @GetMapping(value = "/api/user/wishlist",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<WishlistDto.Get> getWishlist(@RequestParam("id") String id) {
        try {
            List<Wishlist> wishlists = wishlistRepo.findByUserId(Long.parseLong(id));
            List<WishlistDto.Get> result = new ArrayList<>();
            for (Wishlist w : wishlists) {
                result.add(new WishlistDto.Get(w.getId(), w.getUserId(),  trainingRepo.findById(w.getTrainingId()).get().getName()));
            }
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
            User u = userRepo.save(new User(data.getUsername(), data.getPassword(), data.getEmail(), data.getName(), data.getLastname(), data.getDateOfBirth().atStartOfDay(), data.getAddress(), data.getPhoneNum(), LocalDateTime.now(), Role.MEMBER, false));
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

    @PostMapping(value = "/api/user/wishlist",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    WishlistDto.Get addToWishlist(@RequestBody WishlistDto.Add data) {
        try {
            Wishlist w = wishlistRepo.save(new Wishlist(data.getUserId(), data.getTrainingId()));
            WishlistDto.Get result = new WishlistDto.Get(w.getId(), w.getUserId(), trainingRepo.findById(w.getTrainingId()).get().getName());
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

    @PutMapping(value = "/api/user/block",
    produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto.Get blockUser(@RequestParam("id") String id) {
        try {
            User u = userRepo.findById(Long.parseLong(id)).get();
            u.setBlocked(!u.isBlocked());
            userRepo.save(u);
            UserDto.Get result = new UserDto.Get(u.getId(), u.getUsername(), u.getEmail(), u.getName(), u.getLastname(), u.getDateOfBirth(), u.getAddress(), u.getPhoneNum(), u.getRegDateTime(), u.getRole(), u.isBlocked());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping(value = "/api/user/admin",
    produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto.Get makeAdmin(@RequestParam("id") String id) {
        try {
            User u = userRepo.findById(Long.parseLong(id)).get();
            u.setRole(ADMIN);
            userRepo.save(u);
            UserDto.Get result = new UserDto.Get(u.getId(), u.getUsername(), u.getEmail(), u.getName(), u.getLastname(), u.getDateOfBirth(), u.getAddress(), u.getPhoneNum(), u.getRegDateTime(), u.getRole(), u.isBlocked());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping(value = "/api/user/wishlist",
    produces = MediaType.APPLICATION_JSON_VALUE)
    WishlistDto.Get deleteItemFromWishlist(@RequestParam("id") String id) {
        try {
            Wishlist w = wishlistRepo.findById(Long.parseLong(id)).get();
            wishlistRepo.delete(w);
            WishlistDto.Get result = new WishlistDto.Get(w.getId(), w.getUserId(), trainingRepo.findById(w.getTrainingId()).get().getName());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
