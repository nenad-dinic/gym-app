package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingRepo extends JpaRepository<Training, Long> {
    //find all scheduled trainings where hall capacity not filled up, i.e number reservations < hall capacity
    @Query(value = "SELECT DISTINCT t.* FROM training t " +
            "INNER JOIN SCHEDULE s ON t.id = s.training_id " +
            "INNER JOIN hall h ON s.hall_id = h.id " +
            "WHERE (SELECT COUNT(*) FROM reservation_to_schedule r " +
            "WHERE r.schedule_id = s.id) < h.capacity " +
            "ORDER BY t.id DESC;", nativeQuery = true)
    List<Training> getAvailable();

    @Query(value = "SELECT tt.name FROM training t " +
            "INNER JOIN training_to_type ttt ON t.id = ttt.training_id " +
            "INNER JOIN training_type tt ON ttt.type_id = tt.id " +
            "WHERE t.id = :id ;", nativeQuery = true)
    List<String> getTrainingTypes(Long id);
}
