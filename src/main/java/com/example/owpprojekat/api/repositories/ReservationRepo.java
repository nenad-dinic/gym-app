package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {

    List<Reservation> getByUserIdOrderByDateTimeDesc(Long id);

    @Query(value = "SELECT r.* " +
            "FROM reservation r " +
            "INNER JOIN reservation_to_schedule rts ON r.id = rts.reservation_id " +
            "INNER JOIN schedule s ON s.id = rts.schedule_id " +
            "INNER JOIN training t ON t.id = s.training_id " +
            "WHERE r.user_id = :userId AND t.id = :trainingId AND " +
            "s.date < CURDATE() ; ", nativeQuery = true)
    List<Reservation> getByUserIdAndTrainingId(Long userId, Long trainingId);

}
