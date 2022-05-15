package com.example.gymcalculator_2.web;


import com.example.gymcalculator_2.model.Exceptions.InvalidArgumentsException;
import com.example.gymcalculator_2.model.Exceptions.PasswordsDoNotMatchException;
import com.example.gymcalculator_2.model.Exceptions.UsernameAlreadyExistsException;
import com.example.gymcalculator_2.model.Role;
import com.example.gymcalculator_2.model.User;
import com.example.gymcalculator_2.service.AuthService;
import com.example.gymcalculator_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


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
    public String getRegisterPage(@RequestParam(required = false) String error, Model model, HttpServletRequest request) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String user = request.getRemoteUser();
        User currentUser = null;
        if (user != null) {
            currentUser = (User) userService.loadUserByUsername(user);
            model.addAttribute("currentUser", currentUser);
        } else {
            currentUser = new User("defaultUser", "defaultUser@gmail.com", "defaultuser", Role.ROLE_DEFAULT);
            model.addAttribute("currentUser", currentUser);
            currentUser.setProfilePicture("https://st.depositphotos.com/2101611/3925/v/600/depositphotos_39258143-stock-illustration-businessman-avatar-profile-picture.jpg");
        }

        model.addAttribute("bodyContent","register");
        return "register.html";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam(defaultValue = "ROLE_USER") Role role
                          ) {
        try{
            this.userService.register(username, email, password, repeatedPassword,role);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException | UsernameAlreadyExistsException exception ) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}

