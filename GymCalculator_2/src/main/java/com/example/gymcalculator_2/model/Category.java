package com.example.gymcalculator_2.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
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
