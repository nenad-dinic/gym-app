package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.TrainingDto;
import com.example.owpprojekat.api.models.Training;
import com.example.owpprojekat.api.models.TrainingType;
import com.example.owpprojekat.api.repositories.CommentRepo;
import com.example.owpprojekat.api.repositories.TrainingRepo;
import com.example.owpprojekat.api.repositories.TrainingTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TrainingController {

    @Autowired
    TrainingRepo trainingRepo;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    TrainingTypeRepo trainingTypeRepo;

    @GetMapping(value = "/api/training",
    produces = MediaType.APPLICATION_JSON_VALUE)
    TrainingDto.Get getTraining(@RequestParam("id") String id) {
        Training t = trainingRepo.findById(Long.parseLong(id)).get();
        return new TrainingDto.Get(t.getId(), t.getName(), t.getTrainers(), t.getDescription(), t.getPic(), t.getPrice(), t.isGroup(), t.getDifficulty(), t.getDuration(), commentRepo.getRatingForTraining(t.getId()), trainingRepo.getTrainingType(t.getId()));
    }

    @GetMapping(value = "/api/training/available",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<TrainingDto.Get> getAvailableTrainings() {
        List<TrainingDto.Get> result = new ArrayList<>();
        List<Training> trainings = trainingRepo.getAvailable();
        for (Training t : trainings) {
            result.add(new TrainingDto.Get(t.getId(), t.getName(), t.getTrainers(), t.getDescription(), t.getPic(), t.getPrice(), t.isGroup(), t.getDifficulty(), t.getDuration(), commentRepo.getRatingForTraining(t.getId()), trainingRepo.getTrainingType(t.getId())));
        }
        return result;
    }

    @GetMapping(value = "/api/trainings",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<TrainingDto.Get> getTrainings() {
        List<TrainingDto.Get> result = new ArrayList<>();
        List<Training> trainings = trainingRepo.findAll();
        for (Training t : trainings) {
            result.add(new TrainingDto.Get(t.getId(), t.getName(), t.getTrainers(), t.getDescription(), t.getPic(), t.getPrice(), t.isGroup(), t.getDifficulty(), t.getDuration(), commentRepo.getRatingForTraining(t.getId()), trainingRepo.getTrainingType(t.getId())));
        }
        return result;
    }

    @PostMapping(value = "api/training",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    TrainingDto.Get postTraining(@RequestBody TrainingDto.Add data) {
        try {
            Training t = trainingRepo.save(new Training(data.getName(), data.getTrainers(), data.getDescription(), data.getPic(), data.getPrice(), data.isGroup(), data.getDifficulty(), data.getDuration()));
            TrainingDto.Get result = new TrainingDto.Get(t.getId(), t.getName(), t.getTrainers(), t.getDescription(), t.getPic(), t.getPrice(), t.isGroup(), t.getDifficulty(), t.getDuration(), 0, trainingRepo.getTrainingType(t.getId()));
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping(value = "api/training",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    TrainingDto.Get updateTraining(@RequestBody TrainingDto.Update data, @RequestParam ("id") String id) {
        try {
            Training t = trainingRepo.findById(Long.parseLong(id)).get();
            t.setName(data.getName());
            t.setTrainers(data.getTrainers());
            t.setDescription(data.getDescription());
            t.setPic(data.getPic());
            t.setPrice(data.getPrice());
            t.setGroup(data.isGroup());
            t.setDifficulty(data.getDifficulty());
            t.setDuration(data.getDuration());

            trainingRepo.save(t);
            TrainingDto.Get result = new TrainingDto.Get(t.getId(), t.getName(), t.getTrainers(), t.getDescription(), t.getPic(), t.getPrice(), t.isGroup(), t.getDifficulty(), t.getDuration(), commentRepo.getRatingForTraining(t.getId()), trainingRepo.getTrainingType(t.getId()));

            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
