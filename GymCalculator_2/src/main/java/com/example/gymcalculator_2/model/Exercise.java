package com.example.gymcalculator_2.model;


import com.example.gymcalculator_2.model.Enumerator.LiftType;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Exercise")
@Getter
@Setter
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String exerciseName;
    private String categoryName;
    private int weight;
    private int reps;
    private boolean checked;
    private boolean removeable;

    @Enumerated
    private LiftType type;
    public boolean getChecked(){
        return this.checked;
    }
    public Exercise(String exerciseName,String categoryName, LiftType type){
        this.exerciseName = exerciseName;
        this.categoryName = categoryName;
        this.type = type;
        weight = 0;
        reps = 0;
        checked = false;
        removeable = true;
    }
    public Exercise(String exerciseName,String categoryName,int weight,int reps,boolean checked,boolean removeable){
        this.exerciseName = exerciseName;
        this.categoryName = categoryName;
        this.weight=weight;
        this.reps=reps;
        this.checked=checked;
        this.removeable=removeable;
    }

}
