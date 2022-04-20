package com.example.gymcalculator_2.service;

import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.model.LoggedLifts;
import com.example.gymcalculator_2.model.User;
import org.springframework.data.crossstore.ChangeSetPersister;


import java.util.List;

public interface LoggedLiftsService {
    public List<LoggedLifts> listAll();
    public LoggedLifts createNewLift(List<Exercise> loggedExercises);
//    public LoggedLifts findMostRecentLoggedLift(User user) throws ChangeSetPersister.NotFoundException;
}
