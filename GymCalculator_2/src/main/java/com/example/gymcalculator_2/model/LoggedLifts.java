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

    @ElementCollection(targetClass=Double.class,fetch = FetchType.EAGER)
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
    public String getScoreColor(){
        if(totalScore<30) return "#ff0d6d";
        if(totalScore>=30 && totalScore<45) return "#aa00ff";
        if(totalScore>=45 && totalScore<60) return "#140dba";
        if(totalScore>=60 && totalScore<75) return "#20ba9a";
        if(totalScore>=75 && totalScore<87.5) return "#0cba21";
        if(totalScore>=87.5 && totalScore<100) return "#dbdb25";
        if(totalScore>=100 && totalScore<112.5) return "#f69f15";
        if(totalScore>=112.5 && totalScore<125) return "#f64800";
        return "#f6080f";
    }
}
