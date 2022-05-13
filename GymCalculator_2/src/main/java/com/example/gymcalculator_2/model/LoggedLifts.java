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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime loggedDate;


    @OneToMany(fetch = FetchType.LAZY)
    private List<LoggedExercise> loggedExercises;

    public LoggedLifts(List<LoggedExercise> loggedExercises){
        this.loggedExercises = loggedExercises;
        this.loggedDate = LocalDateTime.now();
    }
    public List<LoggedExercise> getExercises() {
        return loggedExercises;
    }
    public String getLoggedDate(){
        return String.format(loggedDate.getDayOfMonth() + "/"+ loggedDate.getMonthValue() + "/" +loggedDate.getYear());
    }
}
