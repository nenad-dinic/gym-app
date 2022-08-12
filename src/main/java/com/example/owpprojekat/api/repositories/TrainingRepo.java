package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingRepo extends JpaRepository<Training, Long> {
    //find all scheduled trainings where hall capacity not filled up, i.e number reservations < hall capacity
    @Query(value = "SELECT t.* FROM training t " +
            "INNER JOIN SCHEDULE s ON t.id = s.training_id " +
            "INNER JOIN hall h ON s.hall_id = h.id " +
            "WHERE (SELECT COUNT(*) FROM reservation r " +
            "WHERE r.schedule_id = s.id) < h.capacity;", nativeQuery = true)
    List<Training> getAvailable();

}
