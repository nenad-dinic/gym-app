package com.example.owpprojekat.api;

import com.example.owpprojekat.api.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBaseConfig {
    private static final Logger log = LoggerFactory.getLogger(DataBaseConfig.class);

    @Bean
    CommandLineRunner initUsers(UserRepo repo) {
        return args -> {
            log.info("Users initialized");
        };
    }

    @Bean
    CommandLineRunner initTrainings(TrainingRepo repo) {
        return args -> {
            log.info("Trainings initialized");
        };
    }

    @Bean
    CommandLineRunner initComments(CommentRepo repo) {
        return args -> {
            log.info("Comments initialized");
        };
    }

    @Bean
    CommandLineRunner initTrainingTypes(TrainingTypeRepo repo) {
        return args -> {
            log.info("Training types initialized");
        };
    }

    @Bean
    CommandLineRunner initReservations(ReservationRepo repo) {
        return args -> {
            log.info("Reservations initialized");
        };
    }
}
