package com.example.gymcalculator_2.service;



import com.example.gymcalculator_2.model.Role;
import com.example.gymcalculator_2.model.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User register(String username, String email, String password, String repeatPassword, Role userRole);
}
