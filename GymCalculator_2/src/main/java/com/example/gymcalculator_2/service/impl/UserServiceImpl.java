package com.example.gymcalculator_2.service.impl;


import com.example.gymcalculator_2.model.User;
import com.example.gymcalculator_2.repository.LoggedLiftsRepository;
import com.example.gymcalculator_2.repository.UserRepository;
import com.example.gymcalculator_2.service.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LoggedLiftsRepository loggedLiftsRepository;
    //private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, LoggedLiftsRepository loggedLiftsRepository) {
        this.userRepository = userRepository;
        //this.passwordEncoder = passwordEncoder;
        this.loggedLiftsRepository = loggedLiftsRepository;
    }

//    @Override
//    public User register(String username, String password) {
//        String encryptedPassword = this.passwordEncoder.encode(password);
//        User user = new User(username,password);
//        return this.userRepository.save(user);
//    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<String> findFriends(User user) throws ChangeSetPersister.NotFoundException {
        User currentUser = userRepository.findByUsername(user.getUsername()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return currentUser.getFriends();
    }

}
