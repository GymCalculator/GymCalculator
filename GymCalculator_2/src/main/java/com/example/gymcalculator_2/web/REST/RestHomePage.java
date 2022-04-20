//package com.example.gymcalculator_2.web.REST;
//
//import com.example.gymcalculator_2.model.User;
//import com.example.gymcalculator_2.service.UserService;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/api/home")
//public class RestHomePage {
//    private final UserService userService;
//
//
//    public RestHomePage(UserService userService) {
//        this.userService = userService;
//    }
//
//
//    @GetMapping("/*")
//    public List<User> findAll(){
//        return this.userService.listUsers();
//    }
//
//}
