package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepo extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findByUserId(Long id);
}
