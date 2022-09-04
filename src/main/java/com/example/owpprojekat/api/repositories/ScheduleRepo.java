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
            "WHERE r.schedule_id = s.id) < h.capacity AND s.training_id = :id " +
            "AND s.date > NOW() + INTERVAL 24 HOUR ;", nativeQuery = true)
    List<Schedule> getSchedulesForTraining(Long id);

    @Query(value = "SELECT * " +
            "FROM ( " +
            "SELECT s.*, t.duration AS duration FROM schedule s INNER JOIN training t ON t.id = s.training_id WHERE s.id IN :ids " +
            ") AS s1, ( " +
            "SELECT s.*, t.duration AS duration FROM schedule s INNER JOIN training t ON t.id = s.training_id WHERE s.id IN :ids " +
            "UNION ALL (SELECT s.*, t.duration AS duration FROM reservation r INNER JOIN reservation_to_schedule rts ON r.id = rts.reservation_id " +
            "INNER JOIN schedule s ON rts.schedule_id = s.id " +
            "INNER JOIN training t ON t.id = s.training_id " +
            "WHERE r.user_id = :id ) ) AS s2 " +
            "WHERE (s1.id != s2.id) AND ( " +
            "(s1.date >= s2.date AND s1.date < DATE_ADD(s2.date, INTERVAL s2.duration MINUTE)) OR " +
            "(DATE_ADD(s1.date, INTERVAL s1.duration MINUTE) > s2.date AND DATE_ADD(s1.date, INTERVAL s1.duration MINUTE) <= DATE_ADD(s2.date, INTERVAL s2.duration MINUTE)) OR " +
            "(s2.date >= s1.date AND s2.date < DATE_ADD(s1.date, INTERVAL s1.duration MINUTE)) OR " +
            "(DATE_ADD(s2.date, INTERVAL s2.duration MINUTE) > s1.date AND DATE_ADD(s2.date, INTERVAL s2.duration MINUTE) <= DATE_ADD(s1.date, INTERVAL s1.duration MINUTE)) " +
            "); ",  nativeQuery = true)
    List<Schedule> getOverlappingSchedules(List<Long> ids, Long id);

    @Query(value = "SELECT s.* FROM schedule s " +
            "INNER JOIN training t ON s.training_id = t.id " +
            "WHERE s.hall_id = :hall_id AND (" +
            "( :date_time >= s.date AND :date_time < DATE_ADD(s.date, INTERVAL t.duration MINUTE)) OR " +
            "(DATE_ADD( :date_time , INTERVAL :duration MINUTE) > s.date AND DATE_ADD( :date_time , INTERVAL :duration MINUTE) <= DATE_ADD( s.date, INTERVAL t.duration MINUTE)) OR " +
            "(s.date >= :date_time AND s.date < DATE_ADD( :date_time , INTERVAL :duration MINUTE)) OR " +
            "(DATE_ADD(s.date, INTERVAL t.duration MINUTE) > :date_time AND DATE_ADD(s.date, INTERVAL t.duration MINUTE) <= DATE_ADD( :date_time , INTERVAL :duration MINUTE)) " +
            ");", nativeQuery = true)
    List<Schedule> getOverlappingSchedulesForHall(Long hall_id, LocalDateTime date_time, int duration);

    @Query(value = "SELECT (SELECT h.capacity FROM hall h " +
            "INNER JOIN schedule s ON h.id = s.hall_id LIMIT 1) - " +
            "(SELECT COUNT(*) FROM schedule s " +
            "INNER JOIN reservation_to_schedule r ON s.id = r.schedule_id " +
            "WHERE s.id = :id) " +
            "FROM schedule WHERE id = :id ;", nativeQuery = true)
    int isFree(Long id);
}
