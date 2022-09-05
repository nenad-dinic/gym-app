package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {

    List<Reservation> getByUserId(Long id);

}
