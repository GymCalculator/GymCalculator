package finki.ukim.mk.gymcalculator.service.impl;

import finki.ukim.mk.gymcalculator.model.User;
import finki.ukim.mk.gymcalculator.repository.UserRepository;
import finki.ukim.mk.gymcalculator.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

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
