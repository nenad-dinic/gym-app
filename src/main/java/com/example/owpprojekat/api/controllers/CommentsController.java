package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.api.dto.CommentDto;
import com.example.owpprojekat.api.models.Comment;
import com.example.owpprojekat.api.repositories.CommentRepo;
import com.example.owpprojekat.api.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

@RestController
public class CommentsController {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UserRepo userRepo;

    @GetMapping(value = "/api/comments/training",
    produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommentDto.Get> getCommentsForTraining(@RequestParam("id") String id) {
        try {
            List<Comment> comments = commentRepo.findAllByTrainingId(parseLong(id));
            List<CommentDto.Get> result = new ArrayList<>();
            for (Comment c : comments) {
                result.add(new CommentDto.Get(c.getId(), c.getText(), c.getRating(), c.getDate(), userRepo.findById(c.getUserId()).get().getUsername(), c.getTrainingId(), c.getStatus(), c.isAnonymous()));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }


}
