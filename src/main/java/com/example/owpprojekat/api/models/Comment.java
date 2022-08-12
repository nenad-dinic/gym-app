package com.example.owpprojekat.api.models;

import com.example.owpprojekat.api.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String text;
    private int rating;
    private Date date;
    private int userId;
    private int trainingId;
    private Status status;
    private Boolean isAnonymous;
}
