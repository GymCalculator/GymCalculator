package com.example.gymcalculator_2.web;


import com.example.gymcalculator_2.model.Exercise;
import com.example.gymcalculator_2.model.LoggedLifts;
import com.example.gymcalculator_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping(value = {"/", "/home"})
public class HomePageController {
    private final UserService userService;

    public HomePageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("bodyContent", "home");
        return "homepage.html";
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
