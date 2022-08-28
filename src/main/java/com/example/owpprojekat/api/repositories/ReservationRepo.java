package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {

}
