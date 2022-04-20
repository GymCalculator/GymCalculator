package com.example.gymcalculator_2.model;


import com.example.gymcalculator_2.model.Enumerator.LiftType;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "LoggedLifts")
public class LoggedLifts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime loggedDate;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Exercise> loggedExercises;

    public LoggedLifts(List<Exercise> loggedExercises){
        this.loggedExercises = loggedExercises;
        this.loggedDate = LocalDateTime.now();
    }

}
