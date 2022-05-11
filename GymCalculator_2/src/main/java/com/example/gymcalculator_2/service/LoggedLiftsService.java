package com.example.gymcalculator_2.service;

import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.model.LoggedExercise;
import com.example.gymcalculator_2.model.LoggedLifts;


import java.util.List;
import java.util.Optional;

public interface LoggedLiftsService {
    public List<LoggedLifts> findAll();
    public LoggedLifts createNewLift(List<LoggedExercise> loggedExercises);
}
