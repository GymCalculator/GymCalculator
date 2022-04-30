package com.example.gymcalculator_2.repository;

import com.example.gymcalculator_2.model.Category;
import com.example.gymcalculator_2.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,String> {
    Optional<Exercise> findByExerciseName(String exerciseName);
    List<Exercise> findAllByCategoryName(String categoryName);

}
