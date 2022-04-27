package com.example.gymcalculator_2.repository;

import com.example.gymcalculator_2.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,String> {
    Exercise findByExerciseName(String exerciseName);
    List<Exercise> findAllByCategoryName(String categoryName);
}
