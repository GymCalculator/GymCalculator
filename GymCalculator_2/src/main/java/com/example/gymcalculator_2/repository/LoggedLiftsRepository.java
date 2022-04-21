package com.example.gymcalculator_2.repository;

import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.model.LoggedLifts;
import com.example.gymcalculator_2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoggedLiftsRepository extends JpaRepository<LoggedLifts,String> {
        Optional<LoggedLifts> findTopByUser(User user);
}
