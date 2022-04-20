package com.example.gymcalculator_2.model;


import com.example.gymcalculator_2.model.Enumerator.LiftType;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table
public class LoggedLifts {
    @Id
    private Long id;


    @ManyToOne
    @JoinColumn(name = "exercise_name_id")
    private Exercises exerciseName;  // todo sredi zbrki

    private int weight;
    private int reps;
    @Enumerated
    private LiftType type;

    public LoggedLifts() {
    }

    public LoggedLifts(int weight, int reps, LiftType type) {
        this.weight = weight;
        this.reps = reps;
        this.type = type;
    }

}
