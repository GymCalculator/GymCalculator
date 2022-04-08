package finki.ukim.mk.gymcalculator.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table
public class User {

    // user's log in info
    @Id
    private String username;
    private String email;
    // todo To Encrypt
    private String password;

    // user's info
    private int bodyweight;
    private int age;
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

    public User() {
    }


}

enum Units {
    Imperial,Metric
}
enum Sex {
    Male,Female,Other
}
