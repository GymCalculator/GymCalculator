package com.example.gymcalculator_2.web;


import com.example.gymcalculator_2.model.Enumerator.LiftType;
import com.example.gymcalculator_2.model.Enumerator.Sex;
import com.example.gymcalculator_2.model.Enumerator.Units;
import com.example.gymcalculator_2.model.Role;
import com.example.gymcalculator_2.model.User;
import com.example.gymcalculator_2.service.CategoryService;
import com.example.gymcalculator_2.service.ExerciseService;
import com.example.gymcalculator_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = {"/", "/home"})
public class HomePageController {
        private final UserService userService;
        private final CategoryService categoryService;
        private final ExerciseService exerciseService;

    public HomePageController(UserService userService, CategoryService categoryService, ExerciseService exerciseService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.exerciseService = exerciseService;
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
}
