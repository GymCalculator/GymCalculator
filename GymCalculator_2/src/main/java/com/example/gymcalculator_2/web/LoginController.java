package com.example.gymcalculator_2.web;

import com.example.gymcalculator_2.model.Exceptions.InvalidUserCredentialsException;
import com.example.gymcalculator_2.model.Role;
import com.example.gymcalculator_2.model.User;
import com.example.gymcalculator_2.service.AuthService;
import com.example.gymcalculator_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public LoginController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getLoginPage(HttpServletRequest request, Model model) {
        model.addAttribute("bodyContent","login");

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

        return "login.html";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user = null;
        try{
            user = this.authService.login(request.getParameter("username"),
                    request.getParameter("email"),
                    request.getParameter("password"));

            request.getSession().setAttribute("user", user);

            return "redirect:/";
        }
        catch (InvalidUserCredentialsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }
}
