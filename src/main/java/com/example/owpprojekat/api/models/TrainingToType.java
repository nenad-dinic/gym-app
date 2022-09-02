package com.example.owpprojekat.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "training_to_type")
public class TrainingToType {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private Long trainingId;
    private Long typeId;

    public TrainingToType(Long trainingId, Long typeId) {
        this.trainingId = trainingId;
        this.typeId = typeId;
    }

    public TrainingToType() {
    }
}
