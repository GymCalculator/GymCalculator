package finki.ukim.mk.gymcalculator.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    private String username;

    private String email;
    // To Encrypt
    private String password;

    @ManyToMany
    private List<User> friends;
    private int bodyweight;
    private int age;
    @OneToMany
    private List<Category> selectedExercises;
    @Enumerated
    private Sex sex;
    @Enumerated
    private Units units;




}
