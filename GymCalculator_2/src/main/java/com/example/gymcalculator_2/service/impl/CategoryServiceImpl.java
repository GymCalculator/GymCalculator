package com.example.gymcalculator_2.service.impl;

import com.example.gymcalculator_2.model.Category;
import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.repository.CategoryRepository;
import com.example.gymcalculator_2.repository.ExerciseRepository;
import com.example.gymcalculator_2.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ExerciseRepository exerciseRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ExerciseRepository exerciseRepository) {
        this.categoryRepository = categoryRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void populateCategories() {
        findAll().forEach(category -> {
                List<Exercise> exercises = exerciseRepository.findAllByCategoryName(category.getCategoryName());
                category.setExercises(exercises);
                categoryRepository.save(category);
                });
    }

    @Override
    public Category findByCategoryName(String categoryName) {

        return categoryRepository.findByCategoryName(categoryName);
    }
}
