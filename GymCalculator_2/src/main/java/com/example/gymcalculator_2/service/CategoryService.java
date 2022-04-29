package com.example.gymcalculator_2.service;

import com.example.gymcalculator_2.model.Category;
import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.Exercise;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    void populateCategories();
    Category findByCategoryName(String categoryName);
    Category addNewExerciseToCategory(Exercise newExercise, String category);
}
