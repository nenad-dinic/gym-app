package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.HallDto;
import com.example.owpprojekat.api.dto.TrainingDto;
import com.example.owpprojekat.api.models.Hall;
import com.example.owpprojekat.api.repositories.HallRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HallController {

    @Autowired
    HallRepo hallRepo;

    @GetMapping(value = "/api/halls",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<HallDto.Get> getHalls() {
        try {
            List<HallDto.Get> result = new ArrayList<>();
            List<Hall> halls = hallRepo.findAll();
            for (Hall h : halls) {
                result.add(new HallDto.Get(h.getId(), h.getTag(), h.getCapacity()));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "/api/hall",
    produces = MediaType.APPLICATION_JSON_VALUE)
    HallDto.Get getHall(@RequestParam("id") String id) {
        try {
            Hall h = hallRepo.findById(Long.parseLong(id)).get();
            HallDto.Get result = new HallDto.Get(h.getId(), h.getTag(), h.getCapacity());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(value = "/api/hall",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    HallDto.Get createHall(@RequestBody HallDto.Add data) {
        try {
            Hall h = hallRepo.save(new Hall(data.getHallTag(), data.getCapacity()));
            HallDto.Get result = new HallDto.Get(h.getId(), h.getTag(), h.getCapacity());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping(value = "/api/hall",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    HallDto.Get updateHall(@RequestBody HallDto.Update data, @RequestParam ("id") String id) {
        try {
            Hall h = hallRepo.findById(Long.parseLong(id)).get();
            h.setCapacity(data.getCapacity());
            hallRepo.save(h);
            HallDto.Get result = new HallDto.Get(h.getId(), h.getTag(), h.getCapacity());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping(value = "/api/hall",
    produces = MediaType.APPLICATION_JSON_VALUE)
    HallDto.Get deleteHall(@RequestParam ("id") String id) {
        try {
            Hall h = hallRepo.findById(Long.parseLong(id)).get();
            hallRepo.delete(h);
            HallDto.Get result = new HallDto.Get(h.getId(), h.getTag(), h.getCapacity());
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
