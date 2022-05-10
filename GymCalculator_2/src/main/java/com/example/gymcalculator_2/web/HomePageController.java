package com.example.gymcalculator_2.web;


import com.example.gymcalculator_2.model.*;
import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.Enumerator.Sex;
import com.example.gymcalculator_2.model.Enumerator.Units;
import com.example.gymcalculator_2.service.CategoryService;
import com.example.gymcalculator_2.service.ExerciseService;
import com.example.gymcalculator_2.service.LoggedLiftsService;
import com.example.gymcalculator_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private User currentUser;

    public HomePageController(UserService userService, CategoryService categoryService, ExerciseService exerciseService, LoggedLiftsService loggedLiftsService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.exerciseService = exerciseService;
        this.loggedLiftsService = loggedLiftsService;

    }

    public User getUser(HttpServletRequest request, Model model) {
        String user = request.getRemoteUser();
        User currentUser = null;
        if (user != null) {
            currentUser = (User) userService.loadUserByUsername(user);
            model.addAttribute("currentUser", currentUser);
        } else {
            currentUser = new User("defaultUser", "defaultUser@gmail.com", "defaultuser", Role.ROLE_DEFAULT);
            model.addAttribute("currentUser", currentUser);
        }
        return currentUser;
    }

    @GetMapping
    public String getHomePage(HttpServletRequest request, Model model) {
        currentUser = getUser(request, model);

        categoryService.populateCategories();
        model.addAttribute("units", Units.values());
        model.addAttribute("sex", Sex.values());
        model.addAttribute("liftType", LiftType.values());
        System.out.println("Logged in as user: " + currentUser.getUsername());
        model.addAttribute("categories", categoryService.findAll());
        return "homepage.html";
    }

    @RequestMapping(params = "AnalyzeAndSave", method = RequestMethod.POST, value = "/homepage")
    public String postHomePage(
//            @RequestParam int units, @RequestParam int nearest,
//                                @RequestParam int sex, @RequestParam int bw, @RequestParam int age,
            @RequestParam List<String> categoryName,
            @RequestParam List<String> exName, @RequestParam(required = false) List<Integer> exWeight,
            @RequestParam(required = false) List<Integer> exReps, HttpServletRequest request) {
        String user = request.getRemoteUser();
        User currentUser = (User) userService.loadUserByUsername(user);


        List<LoggedExercise> loggedExercises = new ArrayList<>();

        for (int i = 0; i < exReps.size(); i++) {
            if (exReps.get(i) != null) {
                loggedExercises.add(exerciseService.addExercise(categoryName.get(i), exName.get(i), exWeight.get(i), exReps.get(i)));
            }
        }
//        System.out.println(exerciseService.findAll());

        LoggedLifts loggedLifts = loggedLiftsService.createNewLift(loggedExercises);

//        System.out.println(loggedLiftsService.findAll());

        userService.addLoggedLifts(user, loggedLifts);

//        User currentUser=(User)userService.loadUserByUsername(user);

        return "redirect:/score";
    }

    @RequestMapping(params = "Analyze", method = RequestMethod.POST, value = "/homepage")
    public String postHomePage() { // todo: only analyze data given


        return "redirect:/score";
    }

    @GetMapping("/score")
    public String getScore(Model model, HttpServletRequest request) {
        String user = request.getRemoteUser();
        User currentUser = (User) userService.loadUserByUsername(user);
        model.addAttribute("userLifts", userService.findMostRecentLoggedLift(currentUser));

        return "score.html";
    }


    // todo: *note: 1 inch = 2.54 cm and 1 kilogram = 2.2 lbs
    @GetMapping("/calculators/one_rep_max")
    public String calc1RM(Model model) {
        model.addAttribute("currentUser", currentUser);

        return "calculators/Calculator1RM.html";
    }

    @PostMapping("/calculators/one_rep_max")
    public String calc1RM(@RequestParam int weight,
                          @RequestParam int reps,
                          Model model) {
        model.addAttribute("currentUser", currentUser);

        List<Integer> calculated1RM = userService.calculate1RMList(weight, reps);
        model.addAttribute("calculated1RM", calculated1RM);
//        System.out.println(calculated1RM);
        return "calculators/Calculator1RM.html";
    }


    @GetMapping("/calculators/wilks")
    public String calcWilks(Model model) {
        model.addAttribute("currentUser", currentUser);

        return "calculators/CalculatorWilks.html";
    }

    @PostMapping("/calculators/wilks")
    public String calcWilks(@RequestParam int bodyweight,
                            @RequestParam int bsquat, @RequestParam int repsSquat,
                            @RequestParam int bpress, @RequestParam int repsBench,
                            @RequestParam int deadlift, @RequestParam int repsDL,
                            @RequestParam int gender,
                            Model model) {
        model.addAttribute("currentUser", currentUser);

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
        model.addAttribute("calculatedWilks", String.format("%.2f", calculatedWilks));
        model.addAttribute("total_weight", weightlifted);

        return "calculators/CalculatorWilks.html";
    }


    @GetMapping("/calculators/TDEE")
    public String calcTDEE(Model model) {
        model.addAttribute("currentUser", currentUser);

        return "calculators/CalculatorTDEE.html";
    }

    @PostMapping("/calculators/TDEE")
    public String calcTDEE(@RequestParam int age,
                           @RequestParam double activitylevel,
                           @RequestParam int bodyweight,
                           @RequestParam int height,
                           @RequestParam int bodyfat,
                           @RequestParam int gender,
                           Model model) {
        model.addAttribute("currentUser", currentUser);

        int calculatedTDEE = userService.calculateTDEE(bodyweight, bodyfat, age, height, gender, activitylevel);
        int calculateTDEE_WithBodyFat = userService.calculateTDEE_WithBodyFat(bodyweight, bodyfat);
        System.out.println(calculatedTDEE);
        model.addAttribute("calculatedTDEE", calculatedTDEE);
        model.addAttribute("calculateTDEE_WithBodyFat", calculateTDEE_WithBodyFat);
        return "calculators/CalculatorTDEE.html";

    }

    @GetMapping("/standards")
    public String strenghtStandards(Model model) {
        model.addAttribute("currentUser", currentUser);

        return "standards/strenghtStandard.html";
    }

    @PostMapping("/standards")
    public String strenghtStandards(@RequestParam(required = false) int age,
                                    @RequestParam int bodyweight,
                                    @RequestParam int units,
                                    @RequestParam int gender,
                                    Model model) {
        model.addAttribute("currentUser", currentUser);
//        int calculateTDEE_WithBodyFat = userService.calculateTDEE_WithBodyFat(bodyweight,bodyfat);
//
//        model.addAttribute("calculateTDEE_WithBodyFat", calculateTDEE_WithBodyFat);
        return "standards/strenghtStandard.html";

    }

}
