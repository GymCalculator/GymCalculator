package com.example.gymcalculator_2.service.impl;


import com.example.gymcalculator_2.model.User;
import com.example.gymcalculator_2.repository.UserRepository;
import com.example.gymcalculator_2.service.UserService;
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


//    @Override
//    public Optional<List<User>> findAllFriends(List<String> friendUsernames) {
//        return Optional.of( userRepository.findAllByUsername(friendUsernames) );
//    }



}
