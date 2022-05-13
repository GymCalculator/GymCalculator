package com.example.gymcalculator_2.repository;

import com.example.gymcalculator_2.model.Category;
import com.example.gymcalculator_2.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    Optional<Category> findByCategoryName(String categoryName);
    void deleteByCategoryName(String categoryName);
}
