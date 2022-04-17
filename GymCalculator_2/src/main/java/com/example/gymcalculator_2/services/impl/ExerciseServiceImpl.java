package com.example.gymcalculator_2.services.impl;

import com.example.gymcalculator_2.repo.ExerciseRepository;
import com.example.gymcalculator_2.services.ExerciseService;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }
}
