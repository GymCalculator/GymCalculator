package com.example.gymcalculator_2.service.impl;



import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.LoggedLifts;
import com.example.gymcalculator_2.model.User;
import com.example.gymcalculator_2.repository.LoggedLiftsRepository;
import com.example.gymcalculator_2.repository.UserRepository;
import com.example.gymcalculator_2.service.LoggedLiftsService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class LoggedLiftsServiceImpl implements LoggedLiftsService {
    private final LoggedLiftsRepository loggedLiftsRepository;

    public LoggedLiftsServiceImpl(LoggedLiftsRepository loggedLiftsRepository) {
        this.loggedLiftsRepository = loggedLiftsRepository;
    }

    @Override
    public List<LoggedLifts> listAll() {
        return loggedLiftsRepository.findAll();
    }

    @Override
    public LoggedLifts createNewLift(int weight, int reps, LiftType type) {
        LoggedLifts newLift = new LoggedLifts(weight,reps,type);
        return loggedLiftsRepository.save(newLift);
    }


}
