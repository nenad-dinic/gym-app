package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.models.Training;
import com.example.owpprojekat.api.repositories.TrainingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainingsController {

    @Autowired
    TrainingRepo trainingRepo;

    @GetMapping(value = "/api/training",
    produces = MediaType.APPLICATION_JSON_VALUE)
    Training getTraining(@RequestParam("id") String id) {
        Training t = trainingRepo.findById(Long.parseLong(id)).get();
        return t;
    }

    @GetMapping(value = "/api/training/available",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<Training> getAvailableTrainings() {
        List<Training> t = trainingRepo.getAvailable();
        return t;
    }
}
