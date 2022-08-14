package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT COALESCE (AVG(rating),0) FROM comment " +
            "WHERE training_id = :id", nativeQuery = true)
    float getRatingForTraining(Long id);

    List<Comment> findAllByTrainingId(Long id);
}
