package finki.ukim.mk.gymcalculator.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    private String categoryName;
    @OneToMany
    private List<Exercise> exercises;

}
