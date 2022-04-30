package com.example.gymcalculator_2.service;

import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.model.LoggedLifts;


import java.util.List;
import java.util.Optional;

public interface LoggedLiftsService {
    public List<LoggedLifts> listAll();
    public LoggedLifts createNewLift(List<Exercise> loggedExercises);
//    public LoggedLifts findMostRecentLoggedLift(User user) throws ChangeSetPersister.NotFoundException;
    public Optional<LoggedLifts> addLifts(List<Exercise> exercises);
}
