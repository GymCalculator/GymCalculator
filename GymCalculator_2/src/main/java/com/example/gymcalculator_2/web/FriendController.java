package com.example.gymcalculator_2.web;

import com.example.gymcalculator_2.model.Role;
import com.example.gymcalculator_2.model.User;
import com.example.gymcalculator_2.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/addFriend")
public class FriendController {
    private final UserService userService;

    public FriendController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getFriends(HttpServletRequest req, Model model){
        String user = req.getRemoteUser();
        User currentUser = (User)userService.loadUserByUsername(user);
        model.addAttribute("currentUser", currentUser);
        return "friend.html";
    }
    @PostMapping
    public String addFriends(@RequestParam String friend,HttpServletRequest req){
        String user = req.getRemoteUser();
        User currentUser = (User)userService.loadUserByUsername(user);
        userService.addNewFriend(currentUser,friend); // TODO: Oops! There was a problem adding {friend} as a friend. Make sure you spelled the username correctly.
        return "redirect:/home";
    }
}
