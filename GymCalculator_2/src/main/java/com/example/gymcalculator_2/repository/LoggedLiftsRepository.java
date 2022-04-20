package com.example.gymcalculator_2.repository;

import com.example.gymcalculator_2.model.LoggedLifts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoggedLiftsRepository extends JpaRepository<LoggedLifts,String> {

}
