package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.TrainingToType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TrainingToTypeRepo extends JpaRepository<TrainingToType, Long> {
    @Transactional
    void deleteByTrainingId(Long id);
}
