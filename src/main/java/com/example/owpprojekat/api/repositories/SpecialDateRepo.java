package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.SpecialDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface SpecialDateRepo extends JpaRepository<SpecialDate, Long> {
    @Query(value = "SELECT * FROM special_date " +
            "WHERE DAY(DATE) = :day AND " +
            "MONTH(DATE) = :month AND " +
            "YEAR(DATE) = :year ; ", nativeQuery = true)
    SpecialDate isDateSpecial(int day, int month, int year);

    SpecialDate findByDate(LocalDateTime date);

}
