package com.example.gymcalculator_2.repository;

import com.example.gymcalculator_2.model.LoggedExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggedExerciseRepository extends JpaRepository<LoggedExercise,Long> {

}
