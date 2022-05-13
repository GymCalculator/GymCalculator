package com.example.gymcalculator_2.service;

import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.model.LoggedExercise;

import javax.transaction.Transactional;
import java.util.List;

public interface ExerciseService {
    List<Exercise> findAll();
    Exercise findByExerciseName(String exerciseName);
    List<Exercise> findAllByCategoryName(String category);
    Exercise createNewExercise(String newExercise, String category, LiftType type);
    public LoggedExercise addExercise (String categoryName, String exerciseName, int weight, int reps); // ako e povikana togas bolean sekad e true, inaku false; todo: type neznam; category neznam
    @Transactional
    void deleteExercise(String exerciseName);
    Exercise editExercise(Exercise exercise,String newExerciseName,LiftType type);
}
