package com.example.gymcalculator_2.service.impl;


import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.model.LoggedExercise;
import com.example.gymcalculator_2.model.LoggedLifts;
import com.example.gymcalculator_2.repository.ExerciseRepository;
import com.example.gymcalculator_2.repository.LoggedLiftsRepository;
import com.example.gymcalculator_2.service.LoggedLiftsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoggedLiftsServiceImpl implements LoggedLiftsService {
    private final LoggedLiftsRepository loggedLiftsRepository;
    private final ExerciseRepository exerciseRepository;

    public LoggedLiftsServiceImpl(LoggedLiftsRepository loggedLiftsRepository, ExerciseRepository exerciseRepository) {
        this.loggedLiftsRepository = loggedLiftsRepository;
        this.exerciseRepository = exerciseRepository;
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

    @Override
    public double calculateTotalScore(Long id) {
        return (double) Math.round(loggedLiftsRepository.getLoggedLiftsById(id).getScoreMap().values()
                .stream().filter(i -> i != 0.0).mapToDouble(d -> d).average().orElse(0.0) * 100) / 100;
    }

    @Override
    public List<Integer> getReps(Long id){
        return loggedLiftsRepository.getLoggedLiftsById(id).getLoggedExercises().stream().map(LoggedExercise::getReps).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getWeights(Long id){
        return loggedLiftsRepository.getLoggedLiftsById(id).getLoggedExercises().stream().map(LoggedExercise::getWeight).collect(Collectors.toList());
    }
    @Override
    public List<String> getExercises(Long id){
        return loggedLiftsRepository.getLoggedLiftsById(id).getLoggedExercises().stream().map(LoggedExercise::getExerciseName).collect(Collectors.toList());
    }

//    @Override
//    public LoggedLifts findMostRecentLoggedLift(User user) throws ChangeSetPersister.NotFoundException {
//        if(user.getLoggedLifts().isEmpty()) re
//        return loggedLiftsRepository.findTopByUser(user);
//    }

}
