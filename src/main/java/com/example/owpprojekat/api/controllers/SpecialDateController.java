package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.SpecialDateDto;
import com.example.owpprojekat.api.models.SpecialDate;
import com.example.owpprojekat.api.repositories.SpecialDateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SpecialDateController {

    @Autowired
    SpecialDateRepo specialDateRepo;

    @GetMapping(value = "/api/specialDates",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<SpecialDateDto.Get> getSpecialDates() {
        try {
            List<SpecialDateDto.Get> result = new ArrayList<>();
            List<SpecialDate> dates = specialDateRepo.findAll();
            for (SpecialDate d : dates) {
                result.add(new SpecialDateDto.Get(d.getId(), d.getName(), d.getDate(), d.getDiscount()));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "/api/specialDate/today",
    produces = MediaType.APPLICATION_JSON_VALUE)
    SpecialDateDto.Get isTodaySpecial() {
        try {
            LocalDateTime today = LocalDateTime.now();
            SpecialDate date = specialDateRepo.isDateSpecial(today.getDayOfMonth(), today.getMonthValue(), today.getYear());
            SpecialDateDto.Get result = new SpecialDateDto.Get(date.getId(), date.getName(), date.getDate(), date.getDiscount());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(value = "/api/specialDate",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    SpecialDateDto.Get postSpecialDate(@RequestBody SpecialDateDto.Add data) {
        try {
            if (specialDateRepo.findByDate(data.getDate().atStartOfDay()) != null) {
                return null;
            }
            SpecialDate sd = specialDateRepo.save(new SpecialDate(data.getName(), data.getDate().atStartOfDay(), data.getDiscount()));
            SpecialDateDto.Get result = new SpecialDateDto.Get(sd.getId(), sd.getName(), sd.getDate(), sd.getDiscount());
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
