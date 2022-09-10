package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.LoyaltyCardDto;
import com.example.owpprojekat.api.dto.LoyaltyCardRequestDto;
import com.example.owpprojekat.api.models.LoyaltyCard;
import com.example.owpprojekat.api.models.LoyaltyCardRequest;
import com.example.owpprojekat.api.repositories.LoyaltyCardRepo;
import com.example.owpprojekat.api.repositories.LoyaltyCardRequestRepo;
import com.example.owpprojekat.api.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoyaltyCardController {

    @Autowired
    LoyaltyCardRequestRepo lcrRepo;

    @Autowired
    LoyaltyCardRepo loyaltyCardRepo;

    @Autowired
    UserRepo userRepo;

    @GetMapping(value = "api/card",
    produces = MediaType.APPLICATION_JSON_VALUE)
    LoyaltyCardDto.Get getLoyaltyCardForUser(@RequestParam("id") String id) {
        try {
            LoyaltyCard lc = loyaltyCardRepo.findByUserId(Long.parseLong(id));
            LoyaltyCardDto.Get result = new LoyaltyCardDto.Get(lc.getId(), lc.getUserId(), lc.getPoints());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "api/card/request",
    produces = MediaType.APPLICATION_JSON_VALUE)
    LoyaltyCardRequestDto.Get getRequestForUser(@RequestParam("id") String id) {
        try {
            LoyaltyCardRequest lcr = lcrRepo.findByUserId(Long.parseLong(id));
            LoyaltyCardRequestDto.Get result = new LoyaltyCardRequestDto.Get(lcr.getId(), userRepo.findById(lcr.getUserId()).get().getUsername());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "/api/card/requests",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<LoyaltyCardRequestDto.Get> getRequests() {
        try {
            List<LoyaltyCardRequestDto.Get> result = new ArrayList<>();
            List<LoyaltyCardRequest> requests = lcrRepo.findAll();
            for (LoyaltyCardRequest lcr : requests) {
                result.add(new LoyaltyCardRequestDto.Get(lcr.getId(), userRepo.findById(lcr.getUserId()).get().getUsername()));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(value = "/api/card/request",
    produces = MediaType.APPLICATION_JSON_VALUE)
    LoyaltyCardRequestDto.Get createRequest(@RequestBody LoyaltyCardRequestDto.Add data) {
        try {
            if (loyaltyCardRepo.findByUserId(data.getUserId()) != null) {
                return null;
            }
            LoyaltyCardRequest lcr = lcrRepo.save(new LoyaltyCardRequest(data.getUserId()));
            LoyaltyCardRequestDto.Get result = new LoyaltyCardRequestDto.Get(lcr.getId(), userRepo.findById(lcr.getUserId()).get().getUsername());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(value = "/api/card/request/accept",
    produces = MediaType.APPLICATION_JSON_VALUE)
    LoyaltyCardDto.Get acceptRequest(@RequestParam("id") String id) {
        try {
            LoyaltyCardRequest request = lcrRepo.findById(Long.parseLong(id)).get();
            lcrRepo.delete(request);
            LoyaltyCard card = loyaltyCardRepo.save(new LoyaltyCard(request.getUserId(), 10));
            LoyaltyCardDto.Get result = new LoyaltyCardDto.Get(card.getId(), card.getUserId(), card.getPoints());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping(value = "/api/card/request/decline",
    produces = MediaType.APPLICATION_JSON_VALUE)
    LoyaltyCardRequestDto.Get declineRequest(@RequestParam("id") String id) {
        try {
            LoyaltyCardRequest request = lcrRepo.findById(Long.parseLong(id)).get();
            lcrRepo.delete(request);
            LoyaltyCardRequestDto.Get result = new LoyaltyCardRequestDto.Get(request.getId(), userRepo.findById(request.getUserId()).get().getUsername());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
