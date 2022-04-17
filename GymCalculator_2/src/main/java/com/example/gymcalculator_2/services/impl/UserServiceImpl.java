package com.example.gymcalculator_2.services.impl;

import com.example.gymcalculator_2.model.User;
import com.example.gymcalculator_2.repo.UserRepository;
import com.example.gymcalculator_2.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }
}
