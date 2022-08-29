package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.LoyaltyCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyCardRepo extends JpaRepository<LoyaltyCard, Long> {
    public LoyaltyCard findByUserId(Long id);
}
