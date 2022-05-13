package com.example.gymcalculator_2.web;

import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.service.CategoryService;
import com.example.gymcalculator_2.service.ExerciseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Exercise")
public class ExerciseController {
    private final CategoryService categoryService;
    private final ExerciseService exerciseService;

    public ExerciseController(CategoryService categoryService, ExerciseService exerciseService) {
        this.categoryService = categoryService;
        this.exerciseService = exerciseService;
    }
    @GetMapping("/addNewExercise")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addNewExercise(@RequestParam String category,Model model) {
        System.out.println(category);
        model.addAttribute("category", category);
        model.addAttribute("liftType", LiftType.values());
        return "addExercise.html";
    }


    @PostMapping("/addNewExercise")
    public String addNewExercise(@RequestParam String exerciseName,
                                 @RequestParam String selectedCategory,
                                 @RequestParam LiftType lifts){
        if(exerciseName == null || selectedCategory == null || lifts == null) return "redirect:/home";
        exerciseService.createNewExercise(exerciseName,selectedCategory,lifts);
        categoryService.addNewExerciseToCategory(exerciseService.findByExerciseName(exerciseName),selectedCategory);
        return "redirect:/home";
    }

    @DeleteMapping("/deleteExercise")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteProduct(@RequestParam String exerciseName) {
       Exercise exerciseToRemove = exerciseService.findByExerciseName(exerciseName);
       categoryService.removeExerciseFromCategory(exerciseToRemove);
        return "redirect:/home";
    }
    @GetMapping("/editExercise")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editExercise(@RequestParam String exerciseName,Model model) {
        model.addAttribute("exerciseToEdit", exerciseName);
        model.addAttribute("liftType", LiftType.values());
        return "editExercise.html";
    }
    @PostMapping("/editExercise")
    public String editExercise(@RequestParam String exerciseName,
                               @RequestParam String newExerciseName,
                               @RequestParam LiftType lifts){
        Exercise exerciseToEdit = exerciseService.findByExerciseName(exerciseName);
        exerciseService.editExercise(exerciseToEdit,newExerciseName,lifts);
        return "redirect:/home";
    }
}
