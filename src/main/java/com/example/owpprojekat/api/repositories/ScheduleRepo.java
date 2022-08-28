package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

    @Query(value = "SELECT s.* FROM schedule s " +
            "INNER JOIN hall h ON s.hall_id = h.id " +
            "WHERE (SELECT COUNT(*) FROM reservation_to_schedule r " +
            "WHERE r.schedule_id = s.id) < h.capacity AND s.training_id = :id", nativeQuery = true)
    List<Schedule> getSchedulesForTraining(Long id);

    @Query(value = "SELECT s.* FROM schedule s " +
            "INNER JOIN training t ON s.training_id = t.id " +
            "WHERE s.hall_id = :hall_id AND (" +
            "( :date_time >= s.date AND :date_time < DATE_ADD(s.date, INTERVAL t.duration MINUTE)) OR " +
            "(DATE_ADD( :date_time , INTERVAL :duration MINUTE) > s.date AND DATE_ADD( :date_time , INTERVAL :duration MINUTE) <= DATE_ADD( s.date, INTERVAL t.duration MINUTE)) OR " +
            "(s.date >= :date_time AND s.date < DATE_ADD( :date_time , INTERVAL :duration MINUTE)) OR " +
            "(DATE_ADD(s.date, INTERVAL t.duration MINUTE) > :date_time AND DATE_ADD(s.date, INTERVAL t.duration MINUTE) <= DATE_ADD( :date_time , INTERVAL :duration MINUTE)) " +
            ");", nativeQuery = true)
    List<Schedule> getOverlappingSchedules(Long hall_id, LocalDateTime date_time, int duration);

    @Query(value = "SELECT (SELECT h.capacity FROM hall h " +
            "INNER JOIN schedule s ON h.id = s.hall_id LIMIT 1) - " +
            "(SELECT COUNT(*) FROM schedule s " +
            "INNER JOIN reservation_to_schedule r ON s.id = r.schedule_id " +
            "WHERE s.id = :id) " +
            "FROM schedule WHERE id = :id ;", nativeQuery = true)
    int isFree(Long id);
}
