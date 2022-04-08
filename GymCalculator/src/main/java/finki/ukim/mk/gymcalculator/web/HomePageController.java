package finki.ukim.mk.gymcalculator.web;


import finki.ukim.mk.gymcalculator.model.User;
import finki.ukim.mk.gymcalculator.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/home")
public class HomePageController {
    private final UserService userService;


    public HomePageController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/*")
    public List<User> findAll(){
        return this.userService.listUsers();
    }



}
