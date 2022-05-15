package com.example.gymcalculator_2.model;

import com.example.gymcalculator_2.model.Enumerator.LiftType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Getter
public class LoggedExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Exercise loggedExercise;
    private int weight;
    private int reps;
    private boolean checked;
    @Enumerated(EnumType.STRING)
    private LiftType type;

    public LoggedExercise(Exercise exercise, int weight, int reps, LiftType type){
        this.loggedExercise = exercise;
        this.weight = weight;
        this.reps = reps;
        this.type = type;
    }
    public boolean getChecked(){
        return this.checked;
    }

    public String getExerciseName() {
        return loggedExercise.getExerciseName();
    }
    public String getCategoryName(){ return loggedExercise.getCategoryName();}
}
