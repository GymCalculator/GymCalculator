package com.example.gymcalculator_2.model;


import com.example.gymcalculator_2.model.Enumerator.LiftType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Setter
@Table(name = "LoggedLifts")
public class LoggedLifts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime loggedDate;

    @OneToMany
    private List<Exercise> loggedExercises;

    public LoggedLifts(List<Exercise> loggedExercises){
        this.loggedExercises = loggedExercises;
        this.loggedDate = LocalDateTime.now();
    }

    public LoggedLifts(LocalDateTime loggedDate,  List<Exercise> loggedExercises) {
        this.loggedDate = loggedDate;

        this.loggedExercises = loggedExercises;
    }
    public List<Exercise> getExercises() {
        return loggedExercises;
    }

}
