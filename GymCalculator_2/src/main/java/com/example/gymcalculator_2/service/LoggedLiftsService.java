package com.example.gymcalculator_2.service;

import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.model.LoggedExercise;
import com.example.gymcalculator_2.model.LoggedLifts;


import java.util.List;
import java.util.Optional;

public interface LoggedLiftsService {
    List<LoggedLifts> findAll();
    LoggedLifts createNewLift(List<LoggedExercise> loggedExercises);

    double calculateTotalScore(Long id);

    List<Integer> getReps(Long id);
    List<Integer> getWeights(Long id);
    List<String> getExercises(Long id);
}
