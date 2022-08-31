package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.LoyaltyCardRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyCardRequestRepo extends JpaRepository<LoyaltyCardRequest, Long> {

    public LoyaltyCardRequest findByUserId(Long id);

}
