package finki.ukim.mk.gymcalculator.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Data
public class Exercise {
    @Id
    private String exerciseName;
    private int weight;
    private int reps;
    @Enumerated
    private Type type;

}
