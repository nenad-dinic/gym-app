package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.TrainingDto;
import com.example.owpprojekat.api.dto.TrainingTypeDto;
import com.example.owpprojekat.api.models.Training;
import com.example.owpprojekat.api.models.TrainingToType;
import com.example.owpprojekat.api.models.TrainingType;
import com.example.owpprojekat.api.repositories.CommentRepo;
import com.example.owpprojekat.api.repositories.TrainingRepo;
import com.example.owpprojekat.api.repositories.TrainingToTypeRepo;
import com.example.owpprojekat.api.repositories.TrainingTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    TrainingToTypeRepo trainingToTypeRepo;

    @GetMapping(value = "/api/training",
    produces = MediaType.APPLICATION_JSON_VALUE)
    TrainingDto.Get getTraining(@RequestParam("id") String id) {
        try {
            Training t = trainingRepo.findById(Long.parseLong(id)).get();
            return new TrainingDto.Get(t.getId(), t.getName(), t.getTrainers(), t.getDescription(), t.getPic(), t.getPrice(), t.isGroup(), t.getDifficulty(), t.getDuration(), commentRepo.getRatingForTraining(t.getId()), trainingRepo.getTrainingTypes(t.getId()));
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "/api/training/available",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<TrainingDto.Get> getAvailableTrainings() {
        try {
            List<TrainingDto.Get> result = new ArrayList<>();
            List<Training> trainings = trainingRepo.getAvailable();
            for (Training t : trainings) {
                result.add(new TrainingDto.Get(t.getId(), t.getName(), t.getTrainers(), t.getDescription(), t.getPic(), t.getPrice(), t.isGroup(), t.getDifficulty(), t.getDuration(), commentRepo.getRatingForTraining(t.getId()), trainingRepo.getTrainingTypes(t.getId())));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "/api/trainings",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<TrainingDto.Get> getTrainings() {
        try {
            List<TrainingDto.Get> result = new ArrayList<>();
            List<Training> trainings = trainingRepo.findAll();
            for (Training t : trainings) {
                result.add(new TrainingDto.Get(t.getId(), t.getName(), t.getTrainers(), t.getDescription(), t.getPic(), t.getPrice(), t.isGroup(), t.getDifficulty(), t.getDuration(), commentRepo.getRatingForTraining(t.getId()), trainingRepo.getTrainingTypes(t.getId())));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(value = "api/training",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    TrainingDto.Get postTraining(@RequestBody TrainingDto.Add data) {

        if (data.getTypes().size() == 0) {
            return null;
        }

        try {
            Training t = trainingRepo.save(new Training(data.getName(), data.getTrainers(), data.getDescription(), data.getPic(), data.getPrice(), data.isGroup(), data.getDifficulty(), data.getDuration()));
            for (Long id : data.getTypes()) {
                try {
                    trainingToTypeRepo.save(new TrainingToType(t.getId(), id));
                } catch (Exception e) {
                    continue;
                }
            }
            TrainingDto.Get result = new TrainingDto.Get(t.getId(), t.getName(), t.getTrainers(), t.getDescription(), t.getPic(), t.getPrice(), t.isGroup(), t.getDifficulty(), t.getDuration(), 0, trainingRepo.getTrainingTypes(t.getId()));

            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping(value = "api/training",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    TrainingDto.Get updateTraining(@RequestBody TrainingDto.Update data, @RequestParam ("id") String id) {

        if (data.getTypes().size() == 0) {
            return null;
        }

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

            trainingToTypeRepo.deleteByTrainingId(t.getId());
            for (Long typeId : data.getTypes()) {
                try {
                    trainingToTypeRepo.save(new TrainingToType(t.getId(), typeId));
                } catch (Exception e) {
                    continue;
                }
            }

            trainingRepo.save(t);
            TrainingDto.Get result = new TrainingDto.Get(t.getId(), t.getName(), t.getTrainers(), t.getDescription(), t.getPic(), t.getPrice(), t.isGroup(), t.getDifficulty(), t.getDuration(), commentRepo.getRatingForTraining(t.getId()), trainingRepo.getTrainingTypes(t.getId()));

            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "api/training/types",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<TrainingTypeDto.Get> getTypes() {
        try {
            List<TrainingType>  types = trainingTypeRepo.findAll();
            List<TrainingTypeDto.Get> result = new ArrayList<>();
            for (TrainingType t : types) {
                result.add(new TrainingTypeDto.Get(t.getId(), t.getName(), t.getDescription()));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
