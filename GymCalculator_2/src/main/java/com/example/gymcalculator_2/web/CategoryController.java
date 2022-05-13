package com.example.gymcalculator_2.web;

import com.example.gymcalculator_2.model.Category;
import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("addNewCategory")
    public String addNewCategory(){
        return "addCategory.html";
    }
    @PostMapping("addNewCategory")
    public String addNewCategory(@RequestParam String category){
        categoryService.createCategory(category);
        return "redirect:/home";
    }
    @DeleteMapping("/deleteCategory")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteProduct(@RequestParam String categoryName) {
        categoryService.removeCategory(categoryName);
        return "redirect:/home";
    }
    @GetMapping("/editCategory")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editExercise(@RequestParam String categoryName, Model model) {
        model.addAttribute("categoryToEdit", categoryName);
        return "editCategory.html";
    }
    @PostMapping("/editCategory")
    public String editExercise(@RequestParam String categoryName,
                               @RequestParam String newCategoryName){
        Category categoryToEdit = categoryService.findByCategoryName(categoryName);
        categoryService.editCategory(categoryToEdit,newCategoryName);
        return "redirect:/home";
    }
}
