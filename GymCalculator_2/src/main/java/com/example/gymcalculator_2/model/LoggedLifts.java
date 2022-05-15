package com.example.gymcalculator_2.model;


import com.example.gymcalculator_2.model.Enumerator.LiftType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Entity
@Data
@NoArgsConstructor
@Setter
@Getter
@Table(name = "LoggedLifts")
public class LoggedLifts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime loggedDate;

    @ElementCollection(targetClass=Double.class)
    @MapKeyColumn(name="Employee_Position")
    private Map<String,Double> scoreMap;

    private double totalScore;

    @OneToMany(fetch = FetchType.EAGER)
    private List<LoggedExercise> loggedExercises;

    public LoggedLifts(List<LoggedExercise> loggedExercises){
        this.loggedExercises = loggedExercises;
        this.loggedDate = LocalDateTime.now();
    }
    public List<LoggedExercise> getExercises() {
        return loggedExercises;
    }
    public String getLoggedDate(){
        return loggedDate.getDayOfMonth() + "/" + loggedDate.getMonthValue() + "/" + loggedDate.getYear();
    }
    public String getLoggedTime(){
        return loggedDate.getHour() + ":" + loggedDate.getMinute();

    }

}
