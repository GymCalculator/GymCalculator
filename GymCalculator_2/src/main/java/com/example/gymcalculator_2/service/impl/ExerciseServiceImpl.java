package com.example.gymcalculator_2.service.impl;

import com.example.gymcalculator_2.model.Category;
import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.Exceptions.ExerciseAlreadyExistsException;
import com.example.gymcalculator_2.model.Exceptions.ExerciseNotFoundException;
import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.model.LoggedExercise;
import com.example.gymcalculator_2.repository.ExerciseRepository;
import com.example.gymcalculator_2.repository.LoggedExerciseRepository;
import com.example.gymcalculator_2.service.ExerciseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final LoggedExerciseRepository loggedExerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, LoggedExerciseRepository loggedExerciseRepository) {
        this.exerciseRepository = exerciseRepository;
        this.loggedExerciseRepository = loggedExerciseRepository;
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
    public LoggedExercise addExercise(String categoryName, String exerciseName, int weight, int reps) {
        LoggedExercise loggedExercise = new LoggedExercise();
        Exercise exercise = exerciseRepository.findByExerciseName(exerciseName).orElseThrow();
        loggedExercise.setLoggedExercise(exercise);
        loggedExercise.setWeight(weight);
        loggedExercise.setChecked(true);
        loggedExercise.setReps(reps);
        return loggedExerciseRepository.save(loggedExercise);
    }

    @Override
    @Transactional
    public void deleteExercise(String exerciseName) {
        exerciseRepository.deleteByExerciseName(exerciseName);
    }

    @Override
    public Exercise editExercise(Exercise exercise, String newExerciseName, LiftType type) {
        if(exerciseRepository.findByExerciseName(newExerciseName).isPresent()) return null;
        exercise.setExerciseName(newExerciseName);
        exercise.setType(type);
        return exerciseRepository.save(exercise);
    }
}
