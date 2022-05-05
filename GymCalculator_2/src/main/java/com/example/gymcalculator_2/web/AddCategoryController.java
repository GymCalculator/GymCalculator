package com.example.gymcalculator_2.web;

import com.example.gymcalculator_2.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/addNewCategory")
public class AddCategoryController {
    private final CategoryService categoryService;

    public AddCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String addNewCategory(){
        return "addCategory.html";
    }
    @PostMapping
    public String addNewCategory(@RequestParam String category){
        categoryService.createCategory(category);
        return "redirect:/home";
    }
}
