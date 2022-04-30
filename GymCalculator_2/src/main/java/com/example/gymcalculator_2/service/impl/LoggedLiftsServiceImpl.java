package com.example.gymcalculator_2.service.impl;


import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.model.LoggedLifts;
import com.example.gymcalculator_2.repository.ExerciseRepository;
import com.example.gymcalculator_2.repository.LoggedLiftsRepository;
import com.example.gymcalculator_2.service.LoggedLiftsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LoggedLiftsServiceImpl implements LoggedLiftsService {
    private final LoggedLiftsRepository loggedLiftsRepository;
    private final ExerciseRepository exerciseRepository;

    public LoggedLiftsServiceImpl(LoggedLiftsRepository loggedLiftsRepository, ExerciseRepository exerciseRepository) {
        this.loggedLiftsRepository = loggedLiftsRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<LoggedLifts> listAll() {
        return loggedLiftsRepository.findAll();
    }

    @Override
    public LoggedLifts createNewLift(List<Exercise> newloggedExercises) {
        LoggedLifts newLift = new LoggedLifts(newloggedExercises);
        return loggedLiftsRepository.save(newLift);
    }

    @Override
    public Optional<LoggedLifts> addLifts(List<Exercise> exercises) {
//        LoggedLifts loggedLifts=loggedLiftsRepository.findTopByUser(currUser).orElseThrow();

        return Optional.of(loggedLiftsRepository.save(new LoggedLifts(LocalDateTime.now(),exercises)));
    }

//    @Override
//    public LoggedLifts findMostRecentLoggedLift(User user) throws ChangeSetPersister.NotFoundException {
//        if(user.getLoggedLifts().isEmpty()) re
//        return loggedLiftsRepository.findTopByUser(user);
//    }

}
