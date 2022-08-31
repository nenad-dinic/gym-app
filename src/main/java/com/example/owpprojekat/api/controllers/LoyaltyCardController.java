package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.LoyaltyCardDto;
import com.example.owpprojekat.api.dto.LoyaltyCardRequestDto;
import com.example.owpprojekat.api.models.LoyaltyCard;
import com.example.owpprojekat.api.models.LoyaltyCardRequest;
import com.example.owpprojekat.api.repositories.LoyaltyCardRepo;
import com.example.owpprojekat.api.repositories.LoyaltyCardRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoyaltyCardController {

    @Autowired
    LoyaltyCardRequestRepo lcrRepo;

    @Autowired
    LoyaltyCardRepo loyaltyCardRepo;

    @PostMapping(value = "/api/card/request",
    produces = MediaType.APPLICATION_JSON_VALUE)
    LoyaltyCardRequestDto.Get createRequest(@RequestBody LoyaltyCardRequestDto.Add data) {
        try {
            if (loyaltyCardRepo.findByUserId(data.getUserId()) != null) {
                return null;
            }
            LoyaltyCardRequest lcr = lcrRepo.save(new LoyaltyCardRequest(data.getUserId()));
            LoyaltyCardRequestDto.Get result = new LoyaltyCardRequestDto.Get(lcr.getId(), lcr.getUserId());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
