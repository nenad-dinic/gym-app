package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.CommentDto;
import com.example.owpprojekat.api.enums.Status;
import com.example.owpprojekat.api.models.Comment;
import com.example.owpprojekat.api.repositories.CommentRepo;
import com.example.owpprojekat.api.repositories.ReservationRepo;
import com.example.owpprojekat.api.repositories.TrainingRepo;
import com.example.owpprojekat.api.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

@RestController
public class CommentsController {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    TrainingRepo trainingRepo;

    @Autowired
    ReservationRepo reservationRepo;

    @GetMapping(value = "/api/comments/training",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommentDto.Get> getCommentsForTraining(@RequestParam("id") String id) {
        try {
            List<Comment> comments = commentRepo.findAllByTrainingId(parseLong(id));
            List<CommentDto.Get> result = new ArrayList<>();
            for (Comment c : comments) {
                result.add(new CommentDto.Get(c.getId(), c.getText(), c.getRating(), c.getDate(), userRepo.findById(c.getUserId()).get().getUsername(), trainingRepo.findById(c.getTrainingId()).get().getName(), c.getStatus(), c.isAnonymous()));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "/api/comments",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommentDto.Get> getComments() {
        try {
            List<Comment> comments = commentRepo.findAll();
            List<CommentDto.Get> result = new ArrayList<>();
            for (Comment c : comments) {
                result.add(new CommentDto.Get(c.getId(), c.getText(), c.getRating(), c.getDate(), userRepo.findById(c.getUserId()).get().getUsername(), trainingRepo.findById(c.getTrainingId()).get().getName(), c.getStatus(), c.isAnonymous()));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(value = "/api/comment",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    CommentDto.Get postComment(@RequestBody CommentDto.Add data) {
        try {
            if (reservationRepo.getByUserIdAndTrainingId(data.getUserId(), data.getTrainingId()).isEmpty()) {
                return null;
            }

            if(!commentRepo.findByUserIdAndTrainingId(data.getUserId(), data.getTrainingId()).isEmpty()) {
                return null;
            }

            Comment c = commentRepo.save(new Comment(data.getText(), data.getRating(), LocalDateTime.now(), data.getUserId(), data.getTrainingId(), Status.PENDING, data.isAnonymous()));
            CommentDto.Get result = new CommentDto.Get(c.getId(), c.getText(), c.getRating(), c.getDate(), userRepo.findById(c.getUserId()).get().getUsername(), trainingRepo.findById(c.getTrainingId()).get().getName(), c.getStatus(), c.isAnonymous());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping(value = "/api/comment/accept",
    produces = MediaType.APPLICATION_JSON_VALUE)
    CommentDto.Get acceptComment(@RequestParam("id") String id) {
        try {
            Comment c = commentRepo.findById(Long.parseLong(id)).get();
            c.setStatus(Status.ACCEPTED);
            commentRepo.save(c);
            CommentDto.Get result = new CommentDto.Get(c.getId(), c.getText(), c.getRating(), c.getDate(), userRepo.findById(c.getUserId()).get().getUsername(), trainingRepo.findById(c.getTrainingId()).get().getName(), c.getStatus(), c.isAnonymous());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping(value = "/api/comment/decline",
    produces = MediaType.APPLICATION_JSON_VALUE)
    CommentDto.Get declineComment(@RequestParam("id") String id) {
        try {
            Comment c = commentRepo.findById(Long.parseLong(id)).get();
            c.setStatus(Status.REJECTED);
            commentRepo.save(c);
            CommentDto.Get result = new CommentDto.Get(c.getId(), c.getText(), c.getRating(), c.getDate(), userRepo.findById(c.getUserId()).get().getUsername(), trainingRepo.findById(c.getTrainingId()).get().getName(), c.getStatus(), c.isAnonymous());
            return result;
        } catch (Exception e) {
            return null;
        }
    }


}
