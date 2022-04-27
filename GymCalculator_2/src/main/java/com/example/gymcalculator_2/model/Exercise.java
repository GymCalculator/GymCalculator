package com.example.gymcalculator_2.model;


import com.example.gymcalculator_2.model.Enumerator.LiftType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Exercise")
@Getter
public class Exercise {
    @Id
    private String exerciseName;
    private String categoryName;
    private int weight;
    private int reps;
    private boolean checked;
    @Enumerated
    private LiftType type;

    public boolean getChecked(){
        return this.checked;
    }
}
