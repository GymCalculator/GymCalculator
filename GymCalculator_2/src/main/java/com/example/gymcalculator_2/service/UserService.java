package com.example.gymcalculator_2.service;


import com.example.gymcalculator_2.model.LoggedLifts;
import com.example.gymcalculator_2.model.Role;
import com.example.gymcalculator_2.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User register(String username, String email, String password, String repeatPassword, Role userRole);

    List<Integer> calculate1RMList(int weight, int reps); // Wathan formula

    int calculate1RM(int weight, int reps);

    double calculateWILK(int bodyweight, int weightlifted, int gender); // WILKS formula

    int calculateTDEE(int bodyweight, int bodyfat, int age, int height, int gender, double activitylevel);
    int calculateTDEE_WithBodyFat(int bodyweight, int bodyfat);
    List<User> addNewFriend(User user, String friend);
    void setUserSettings(String currUsername,int units,int neareast,String sex,int bw,int age);

    void addLoggedLifts(String userId, LoggedLifts loggedLifts);

    LoggedLifts findMostRecentLoggedLift(User userId);
    List<LoggedLifts> getLoggedLifts(User user);

    void calculateStrenghtStandard(int weight,int reps,int bodyweight,int gender);
}
