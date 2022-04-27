package com.example.gymcalculator_2.model;

import com.example.gymcalculator_2.model.Enumerator.Proficiency;
import com.example.gymcalculator_2.model.Enumerator.Sex;
import com.example.gymcalculator_2.model.Enumerator.Units;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Getter
public class User implements UserDetails {

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

//    @Enumerated
//    @Nullable
//    private Proficiency proficiency; // Untrained, Novice, Intermediate, Proficient, Advanced, Exceptional, Elite, World-class

    @Enumerated
    private Role role;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;


    public User(String username, String email, String password,Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
