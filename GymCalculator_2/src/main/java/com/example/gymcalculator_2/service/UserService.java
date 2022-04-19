package com.example.gymcalculator_2.service;



import com.example.gymcalculator_2.model.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface UserService {
    //User register(String username, String password);
    List<User> listUsers();
    List<String> findFriends(User user) throws ChangeSetPersister.NotFoundException;

}
