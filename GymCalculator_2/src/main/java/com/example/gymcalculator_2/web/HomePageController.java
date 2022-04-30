package com.example.gymcalculator_2.web;


import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.Enumerator.Sex;
import com.example.gymcalculator_2.model.Enumerator.Units;
import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.model.LoggedLifts;
import com.example.gymcalculator_2.model.Role;
import com.example.gymcalculator_2.model.User;
import com.example.gymcalculator_2.service.CategoryService;
import com.example.gymcalculator_2.service.ExerciseService;
import com.example.gymcalculator_2.service.LoggedLiftsService;
import com.example.gymcalculator_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = {"/", "/home"})
public class HomePageController {

        private final UserService userService;
        private final CategoryService categoryService;
        private final ExerciseService exerciseService;
        private final LoggedLiftsService loggedLiftsService;

    public HomePageController(UserService userService, CategoryService categoryService, ExerciseService exerciseService, LoggedLiftsService loggedLiftsService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.exerciseService = exerciseService;
        this.loggedLiftsService = loggedLiftsService;
    }

    @GetMapping
    public String getHomePage(HttpServletRequest request, Model model) {
        String user = request.getRemoteUser();
        User currentUser = null;
        if(user != null) {
            currentUser = (User)userService.loadUserByUsername(user);
            model.addAttribute("currentUser", currentUser);
        }
        else {
            currentUser = new User("defaultUser", "defaultUser@gmail.com", "defaultuser", Role.ROLE_DEFAULT);
            model.addAttribute("currentUser", currentUser);
        }
        categoryService.populateCategories();
        model.addAttribute("units", Units.values());
        model.addAttribute("sex", Sex.values());
        model.addAttribute("liftType", LiftType.values());
        System.out.println("Logged in as user: " + currentUser.getUsername());
        model.addAttribute("categories",categoryService.findAll());
        return "homepage.html";
    }

    @PostMapping("/homepage")
    public String postHomePage(
//            @RequestParam int units, @RequestParam int nearest,
//                                @RequestParam int sex, @RequestParam int bw, @RequestParam int age,
                                @RequestParam List<String> categoryName,
                                @RequestParam List<String> exName, @RequestParam List<Integer> exWeight,
                                @RequestParam List<Integer> exReps,HttpServletRequest request){
        String user = request.getRemoteUser();

        exName.forEach(System.out::println);
        exReps.forEach(System.out::println);

//        User currentUser=(User)userService.loadUserByUsername(user);
        List<Exercise> exercises = new ArrayList<>();
        for (int i = 0; i < exName.size(); i++) {
            exercises.add(exerciseService.addExercise(categoryName.get(i),exName.get(i),exWeight.get(i),exReps.get(i)));
        }
        LoggedLifts loggedLifts = loggedLiftsService.addLifts(exercises).orElseThrow();

        userService.addLoggedLifts(user, loggedLifts);

        return "redirect:/score.html";
    }

    @GetMapping("/score")
    public String getScore(Model model,HttpServletRequest request){
        String user = request.getRemoteUser();

//        model.addAttribute("userLifts",userService.getLoggedLifts(user));
        return "score.html";
    }


// todo: *note: 1 inch = 2.54 cm and 1 kilogram = 2.2 lbs
    @GetMapping("/calculator/one_rep_max")
    public String calc1RM() {
        return "calculators/Calculator1RM.html";
    }

    @PostMapping("/calculator/one_rep_max")
    public String calc1RM(@RequestParam int weight,
                          @RequestParam int reps,
                          Model model) {
        List<Integer> calculated1RM = userService.calculate1RMList(weight, reps);
        model.addAttribute("calculated1RM", calculated1RM);
//        System.out.println(calculated1RM);
        return "calculators/Calculator1RM.html";
    }


    @GetMapping("/calculator/wilks")
    public String calcWilks() {
        return "calculators/CalculatorWilks.html";
    }

    @PostMapping("/calculator/wilks")
    public String calcWilks(@RequestParam int bodyweight,
                            @RequestParam int bsquat, @RequestParam int repsSquat,
                            @RequestParam int bpress, @RequestParam int repsBench,
                            @RequestParam int deadlift, @RequestParam int repsDL,
                            @RequestParam int gender,
                            Model model) {
        int squat1RM = bsquat;
        if (repsSquat > 1) {
            squat1RM = userService.calculate1RM(bsquat, repsSquat);
        }
        int bench1RM = bsquat;
        if (repsBench > 1) {
            bench1RM = userService.calculate1RM(bpress, repsBench);
        }
        int deadlift1RM = bsquat;
        if (repsDL > 1) {
            deadlift1RM = userService.calculate1RM(deadlift, repsDL);
        }
        int weightlifted = squat1RM + bench1RM + deadlift1RM;
        System.out.println(gender);
        double calculatedWilks = userService.calculateWILK(bodyweight, weightlifted, gender);
        model.addAttribute("calculatedWilks", String.format("%.2f",calculatedWilks));
        model.addAttribute("total_weight", weightlifted);

        return "calculators/CalculatorWilks.html";
    }


    @GetMapping("/calculator/TDEE")
    public String calcTDEE() {
        return "calculators/CalculatorTDEE.html";
    }

    @PostMapping("/calculator/TDEE")
    public String calcTDEE(@RequestParam int age,
                           @RequestParam double activitylevel,
                           @RequestParam int bodyweight,
                           @RequestParam int height,
                           @RequestParam int bodyfat,
                           @RequestParam int gender,
                           Model model) {

        int calculatedTDEE = userService.calculateTDEE(bodyweight,bodyfat,age,height,gender,activitylevel);
        int calculateTDEE_WithBodyFat = userService.calculateTDEE_WithBodyFat(bodyweight,bodyfat);
        System.out.println(calculatedTDEE);
        model.addAttribute("calculatedTDEE", calculatedTDEE);
        model.addAttribute("calculateTDEE_WithBodyFat", calculateTDEE_WithBodyFat);
        return "calculators/CalculatorTDEE.html";

    }

    @GetMapping("/standards")
    public String strenghtStandards() {
        return "standards/strenghtStandards.html";
    }

    @PostMapping("/standards")
    public String strenghtStandards(@RequestParam(required = false) int age,
                           @RequestParam int bodyweight,
                           @RequestParam int units,
                           @RequestParam int gender,
                           Model model) {
//        int calculateTDEE_WithBodyFat = userService.calculateTDEE_WithBodyFat(bodyweight,bodyfat);
//
//        model.addAttribute("calculateTDEE_WithBodyFat", calculateTDEE_WithBodyFat);
        return "standards/strenghtStandards.html";

    }

}
