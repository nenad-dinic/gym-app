package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.ReservationToSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationToScheduleRepo extends JpaRepository<ReservationToSchedule, Long> {

    @Query(value = "SELECT rts.* FROM reservation_to_schedule rts " +
            "INNER JOIN schedule s ON s.id = rts.schedule_id " +
            "WHERE rts.schedule_id = :sId AND rts.reservation_id = :rId AND " +
            "s.date > NOW() + INTERVAL 24 HOUR ;", nativeQuery = true)
    ReservationToSchedule getReservationToScheduleLess24H(Long sId, Long rId);

    @Query(value = "SELECT rts.* " +
            "FROM reservation_to_schedule rts " +
            "INNER JOIN schedule s ON s.id = rts.schedule_id " +
            "INNER JOIN training t ON t.id = s.training_id " +
            "WHERE t.id = :id ;", nativeQuery = true)
    List<ReservationToSchedule> getReservationsForTraining(Long id);
}
