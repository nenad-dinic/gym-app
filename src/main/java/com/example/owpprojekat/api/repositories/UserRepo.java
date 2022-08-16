package com.example.owpprojekat.api.repositories;

import com.example.owpprojekat.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);

}
