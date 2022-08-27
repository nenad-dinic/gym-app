package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.CartItemDto;
import com.example.owpprojekat.api.dto.ScheduleDto;
import com.example.owpprojekat.api.models.Schedule;
import com.example.owpprojekat.api.models.Training;
import com.example.owpprojekat.api.repositories.ScheduleRepo;
import com.example.owpprojekat.api.repositories.TrainingRepo;
import com.example.owpprojekat.front.data.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CartController {

    @Autowired
    TrainingRepo trainingRepo;

    @Autowired
    ScheduleRepo scheduleRepo;

    @PostMapping(value = "/api/cart",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<CartItemDto.Get> getCartItems(@RequestBody CartItemDto.CartItems cart) {
        List<CartItemDto.Get> cartItems = new ArrayList<>();
        for (Long id : cart.getItems()) {
            try {
                Schedule s = scheduleRepo.findById(id).get();
                Training t = trainingRepo.findById(s.getTrainingId()).get();
                cartItems.add(new CartItemDto.Get(s.getId(), t.getName(), t.getTrainers(), trainingRepo.getTrainingTypes(t.getId()), s.getDate(), t.getPrice()));
            } catch (Exception e) {
                continue;
            }
        }
        return cartItems;
    }




}
