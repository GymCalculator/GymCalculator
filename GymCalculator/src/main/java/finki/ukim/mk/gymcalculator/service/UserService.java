package finki.ukim.mk.gymcalculator.service;

import finki.ukim.mk.gymcalculator.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {
//    Optional<List<User>> findAllFriends(List<String> friendUsernames);
    List<User> listUsers();

}
