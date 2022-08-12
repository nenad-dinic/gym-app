package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

    @Query(value = "SELECT s.* FROM schedule s " +
            "INNER JOIN hall h ON s.hall_id = h.id " +
            "WHERE (SELECT COUNT(*) FROM reservation r " +
            "WHERE r.schedule_id = s.id) < h.capacity AND s.training_id = :id", nativeQuery = true)
    List<Schedule> getSchedulesForTraining(Long id);
}
