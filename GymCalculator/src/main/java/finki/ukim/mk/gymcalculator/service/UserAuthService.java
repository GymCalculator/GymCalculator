package finki.ukim.mk.gymcalculator.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.management.relation.Role;

public interface UserAuthService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}

