package com.example.gymcalculator_2.service;

import com.example.gymcalculator_2.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    void populateCategories();
    Category findByCategoryName(String categoryName);
}
