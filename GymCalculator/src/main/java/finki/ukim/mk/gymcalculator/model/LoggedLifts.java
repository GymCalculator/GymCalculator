package finki.ukim.mk.gymcalculator.model;

import lombok.Data;

import javax.naming.Name;
import javax.persistence.*;
import java.util.Arrays;
import java.util.List;


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
    private Type type;

    public LoggedLifts() {
    }

}
enum Type {
    None,Standard,Weighted,Assisted
}
