package com.example.gymcalculator_2.service.impl;

import com.example.gymcalculator_2.model.Category;
import com.example.gymcalculator_2.model.Exceptions.CategoryNotFoundException;
import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.model.LoggedExercise;
import com.example.gymcalculator_2.model.LoggedLifts;
import com.example.gymcalculator_2.repository.CategoryRepository;
import com.example.gymcalculator_2.repository.ExerciseRepository;
import com.example.gymcalculator_2.repository.LoggedExerciseRepository;
import com.example.gymcalculator_2.repository.LoggedLiftsRepository;
import com.example.gymcalculator_2.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ExerciseRepository exerciseRepository;
    private final LoggedExerciseRepository loggedExerciseRepository;
    private final LoggedLiftsRepository loggedLiftsRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ExerciseRepository exerciseRepository, LoggedExerciseRepository loggedExerciseRepository, LoggedLiftsRepository loggedLiftsRepository) {
        this.categoryRepository = categoryRepository;
        this.exerciseRepository = exerciseRepository;
        this.loggedExerciseRepository = loggedExerciseRepository;
        this.loggedLiftsRepository = loggedLiftsRepository;
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

        return categoryRepository.findByCategoryName(categoryName).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Category addNewExerciseToCategory(Exercise exercise, String category) {
        Category desiredCategory = findByCategoryName(category);
        desiredCategory.getExercises().add(exercise);
        return categoryRepository.save(desiredCategory);
    }

    @Override
    public Category createCategory(String categoryName) {
        if(categoryRepository.findByCategoryName(categoryName).isPresent()) throw new CategoryNotFoundException();
        return categoryRepository.save(new Category(categoryName));
    }

    @Override
    public void removeExerciseFromCategory(Exercise exerciseToRemove) {
        Category category = findByCategoryName(exerciseToRemove.getCategoryName());
        category.getExercises().remove(exerciseToRemove);
        List<LoggedLifts> loggedLifts = loggedLiftsRepository.findAll();
        for(LoggedLifts ll : loggedLifts) {
            List<LoggedExercise> loggedExercises = ll.getExercises().stream()
                    .filter(l -> !l.getExerciseName().equals(exerciseToRemove.getExerciseName()))
                    .collect(Collectors.toList());
            ll.setLoggedExercises(loggedExercises);
            loggedLiftsRepository.save(ll);
        }
        loggedExerciseRepository.deleteAll(loggedExerciseRepository.findAllByLoggedExercise(exerciseToRemove));
        exerciseRepository.delete(exerciseToRemove);
    }

    @Override
    public Category editCategory(Category category,String newCategoryName) {
        exerciseRepository.findAllByCategoryName(category.getCategoryName())
                .forEach(exercise -> exercise.setCategoryName(newCategoryName));
        category.setCategoryName(newCategoryName);
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void removeCategory(String category) {
        categoryRepository.deleteByCategoryName(category);
    }


}
