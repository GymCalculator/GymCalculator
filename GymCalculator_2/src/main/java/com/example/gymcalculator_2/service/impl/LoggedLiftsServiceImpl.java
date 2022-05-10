package com.example.gymcalculator_2.service.impl;


import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.model.LoggedExercise;
import com.example.gymcalculator_2.model.LoggedLifts;
import com.example.gymcalculator_2.model.User;
import com.example.gymcalculator_2.repository.LoggedLiftsRepository;
import com.example.gymcalculator_2.repository.UserRepository;
import com.example.gymcalculator_2.service.LoggedLiftsService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class LoggedLiftsServiceImpl implements LoggedLiftsService {
    private final LoggedLiftsRepository loggedLiftsRepository;

    public LoggedLiftsServiceImpl(LoggedLiftsRepository loggedLiftsRepository) {
        this.loggedLiftsRepository = loggedLiftsRepository;
    }

    @Override
    public List<LoggedLifts> findAll() {
        return loggedLiftsRepository.findAll();
    }

    @Override
    public LoggedLifts createNewLift(List<LoggedExercise> newloggedExercises) {

        LoggedLifts newLift = new LoggedLifts(newloggedExercises);
        return loggedLiftsRepository.save(newLift);
    }



//    @Override
//    public LoggedLifts findMostRecentLoggedLift(User user) throws ChangeSetPersister.NotFoundException {
//        if(user.getLoggedLifts().isEmpty()) re
//        return loggedLiftsRepository.findTopByUser(user);
//    }

}
