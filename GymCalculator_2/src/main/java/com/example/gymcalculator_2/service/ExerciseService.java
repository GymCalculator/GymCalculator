package com.example.gymcalculator_2.service;

import com.example.gymcalculator_2.model.Exercise;

import java.util.List;

public interface ExerciseService {
    List<Exercise> findAll();
    Exercise findByExerciseName(String exerciseName);
}
