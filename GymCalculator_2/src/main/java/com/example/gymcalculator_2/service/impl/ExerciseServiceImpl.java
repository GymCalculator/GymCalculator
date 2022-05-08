package com.example.gymcalculator_2.service.impl;

import com.example.gymcalculator_2.model.Category;
import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.Exceptions.ExerciseAlreadyExistsException;
import com.example.gymcalculator_2.model.Exceptions.ExerciseNotFoundException;
import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.repository.ExerciseRepository;
import com.example.gymcalculator_2.service.CategoryService;
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
        return exerciseRepository.findByExerciseName(exerciseName).orElseThrow(ExerciseNotFoundException::new);
    }

    @Override
    public List<Exercise> findAllByCategoryName(String category) {
        return exerciseRepository.findAllByCategoryName(category);
    }

    @Override
    public Exercise createNewExercise(String newExercise, String category, LiftType type) {
        if(exerciseRepository.findByExerciseName(newExercise).isPresent()) throw new ExerciseAlreadyExistsException();
            return exerciseRepository.save(new Exercise(newExercise, category, type));

    }
    @Override
    public Exercise addExercise(String categoryName, String exerciseName, int weight, int reps) {
        Exercise exercise = exerciseRepository.findByExerciseName(exerciseName).orElseThrow();
        // categoryName already set
        exercise.setWeight(weight);
        exercise.setChecked(true);
        exercise.setReps(reps);

        exerciseRepository.save(exercise);

        return exercise;
    }

}
