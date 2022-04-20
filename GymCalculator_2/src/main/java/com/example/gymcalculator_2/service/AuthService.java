package com.example.gymcalculator_2.service;

import com.example.gymcalculator_2.model.User;

public interface AuthService {
    User login(String username, String email, String password);
}

