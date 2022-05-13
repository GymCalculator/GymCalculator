package com.example.gymcalculator_2.repository;

import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.model.LoggedExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoggedExerciseRepository extends JpaRepository<LoggedExercise,Long> {
    List<LoggedExercise> findAllByLoggedExercise(Exercise exercise);
    void deleteByLoggedExercise(Exercise exercise);
}
