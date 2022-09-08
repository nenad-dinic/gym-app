package com.example.owpprojekat.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReportDto {

    @Getter
    @Setter
    public static class Get {
        private String trainingName;
        private String trainers;
        private int numOfRes;
        private int priceSum;

        public Get(String trainingName, String trainers, int numOfRes, int priceSum) {
            this.trainingName = trainingName;
            this.trainers = trainers;
            this.numOfRes = numOfRes;
            this.priceSum = priceSum;
        }

        public Get() {
        }
    }

    @Getter
    @Setter
    public static class Request {
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate dateFrom;
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate dateTo;

        public Request(LocalDate dateFrom, LocalDate dateTo) {
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
        }

        public Request() {
        }
    }

}
