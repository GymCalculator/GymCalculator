package com.example.gymcalculator_2.service;

import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.model.LoggedExercise;
import com.example.gymcalculator_2.model.LoggedLifts;
import com.example.gymcalculator_2.model.User;
import org.springframework.data.crossstore.ChangeSetPersister;


import java.util.List;
import java.util.Optional;

public interface LoggedLiftsService {
    public List<LoggedLifts> findAll();
    public LoggedLifts createNewLift(List<LoggedExercise> loggedExercises);
}
