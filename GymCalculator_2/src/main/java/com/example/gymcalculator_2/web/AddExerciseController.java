package com.example.gymcalculator_2.web;

import com.example.gymcalculator_2.model.Category;
import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.Exceptions.ExerciseAlreadyExistsException;
import com.example.gymcalculator_2.service.CategoryService;
import com.example.gymcalculator_2.service.ExerciseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/addNewExercise")
public class AddExerciseController {
    private final CategoryService categoryService;
    private final ExerciseService exerciseService;

    public AddExerciseController(CategoryService categoryService, ExerciseService exerciseService) {
        this.categoryService = categoryService;
        this.exerciseService = exerciseService;
    }
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addNewExercise(@RequestParam String category,Model model) {
        System.out.println(category);
        model.addAttribute("category", category);
        model.addAttribute("liftType", LiftType.values());
        return "addExercise.html";
    }


    @PostMapping()
    public String addNewExercise(@RequestParam String exerciseName,
                                 @RequestParam String selectedCategory,
                                 @RequestParam LiftType lifts){
        if(exerciseName == null || selectedCategory == null || lifts == null) return "redirect:/home";
        exerciseService.createNewExercise(exerciseName,selectedCategory,lifts);
        categoryService.addNewExerciseToCategory(exerciseService.findByExerciseName(exerciseName),selectedCategory);
        return "redirect:/home";
    }
}
