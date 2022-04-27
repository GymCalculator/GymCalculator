package com.example.gymcalculator_2.service.impl;

import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.repository.ExerciseRepository;
import com.example.gymcalculator_2.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<Exercise> findAll(){
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise findByExerciseName(String exerciseName) {
        return exerciseRepository.findByExerciseName(exerciseName);
    }


}
