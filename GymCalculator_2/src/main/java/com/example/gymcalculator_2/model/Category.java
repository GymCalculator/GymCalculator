package com.example.gymcalculator_2.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "Category")
public class Category {
    @Id
    private String categoryName;
    @OneToMany
    private List<Exercise> exercises;

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
