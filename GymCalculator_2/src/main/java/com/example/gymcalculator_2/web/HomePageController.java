package com.example.gymcalculator_2.web;


import com.example.gymcalculator_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = {"/", "/home"})
public class HomePageController {
    private final UserService userService;

    public HomePageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("bodyContent", userService.listUsers());
        return "homepage.html";
    }
}
