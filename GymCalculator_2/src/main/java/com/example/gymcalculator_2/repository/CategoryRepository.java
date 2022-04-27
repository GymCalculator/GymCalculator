package com.example.gymcalculator_2.repository;

import com.example.gymcalculator_2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    Category findByCategoryName(String categoryName);
}
