package com.example.gymcalculator_2.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Category")
public class Category {
    @Id
    private String categoryName;
    @OneToMany
    private List<Exercise> exercises;

}
