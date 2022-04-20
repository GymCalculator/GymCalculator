package com.example.gymcalculator_2.model;

import com.example.gymcalculator_2.model.Enumerator.Sex;
import com.example.gymcalculator_2.model.Enumerator.Units;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    // user's log in info
    @Id
    private String username;
    private String email;
    // todo To Encrypt
    private String password;

    // user's info
    private int bodyweight;
    private LocalDateTime userAge;
    @Enumerated
    private Sex sex;
    @Enumerated
    private Units units;

    // user's friends
    @ElementCollection
    private List<String> friends; //todo da se cuva samo lista od usernames (pomalku mesto zafakja; samo barame username vo service delot)

    // user's selected + logged lifts
    @OneToMany
    private List<LoggedLifts> loggedLifts;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
