package com.example.gymcalculator_2.service;

import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.LoggedLifts;


import java.util.List;

public interface LoggedLiftsService {
    public List<LoggedLifts> listAll();
    LoggedLifts createNewLift(int weight, int reps, LiftType type);
}
