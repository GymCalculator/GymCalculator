package com.example.gymcalculator_2.web;

import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.Exceptions.ExerciseAlreadyExistsException;
import com.example.gymcalculator_2.service.CategoryService;
import com.example.gymcalculator_2.service.ExerciseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String NoRoute(){
        return "redirect:/";
    }

    @PostMapping
    public String addNewExercise(@RequestParam(required = false) String newExercise,
                                 @RequestParam(required = false) String category,
                                 @RequestParam(required = false) LiftType liftType){
        System.out.println("add new exercise " + newExercise + category + liftType);
//        if(newExercise == null || category == null || liftType == null) return "";
//        exerciseService.createNewExercise(newExercise,category,liftType);
//        categoryService.addNewExerciseToCategory(exerciseService.findByExerciseName(newExercise),category);
        return "homepage.html";
    }
}
