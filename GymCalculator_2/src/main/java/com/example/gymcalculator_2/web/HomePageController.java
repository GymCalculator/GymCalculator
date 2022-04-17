package com.example.gymcalculator_2.web;


import com.example.gymcalculator_2.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = {"/", "/home"})
public class HomePageController {
    private final UserService userService;

    public HomePageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/listAll")
    public String listAll(Model model){
        model.addAttribute("users",userService.listUsers());
        return "homepage.html";
    }


}
