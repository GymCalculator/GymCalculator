package com.example.gymcalculator_2.service.impl;

import com.example.gymcalculator_2.model.Exceptions.InvalidArgumentsException;
import com.example.gymcalculator_2.model.Exceptions.InvalidUserCredentialsException;
import com.example.gymcalculator_2.model.User;
import com.example.gymcalculator_2.repository.UserRepository;
import com.example.gymcalculator_2.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String email, String password) {
        if (username==null || username.isEmpty() || email==null || email.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
