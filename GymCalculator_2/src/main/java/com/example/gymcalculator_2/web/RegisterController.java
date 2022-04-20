package com.example.gymcalculator_2.web;


import com.example.gymcalculator_2.model.Exceptions.InvalidArgumentsException;
import com.example.gymcalculator_2.model.Exceptions.PasswordsDoNotMatchException;
import com.example.gymcalculator_2.model.Role;
import com.example.gymcalculator_2.service.AuthService;
import com.example.gymcalculator_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "register.html";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword
                          ) {
        try{
            this.userService.register(username, email, password, repeatedPassword);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}

