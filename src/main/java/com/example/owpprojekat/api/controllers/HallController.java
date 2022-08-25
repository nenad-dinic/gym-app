package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.HallDto;
import com.example.owpprojekat.api.models.Hall;
import com.example.owpprojekat.api.repositories.HallRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HallController {

    @Autowired
    HallRepo hallRepo;

    @GetMapping(value = "/api/halls",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<HallDto.Get> getHalls() {
        List<HallDto.Get> result = new ArrayList<>();
        List<Hall> halls = hallRepo.findAll();
        for (Hall h : halls) {
            result.add(new HallDto.Get(h.getId(), h.getTag(), h.getCapacity()));
        }
        return result;
    }
}
