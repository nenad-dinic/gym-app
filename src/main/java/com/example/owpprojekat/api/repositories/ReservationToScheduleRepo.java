package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.ReservationToSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationToScheduleRepo extends JpaRepository<ReservationToSchedule, Long> {
}
