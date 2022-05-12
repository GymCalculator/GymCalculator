package com.example.gymcalculator_2.service.impl;

import com.example.gymcalculator_2.model.Enumerator.Sex;
import com.example.gymcalculator_2.model.Enumerator.Units;
import com.example.gymcalculator_2.model.Exceptions.*;
import com.example.gymcalculator_2.model.LoggedLifts;
import com.example.gymcalculator_2.model.Role;
import com.example.gymcalculator_2.model.User;
import com.example.gymcalculator_2.repository.UserRepository;
import com.example.gymcalculator_2.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User register(String username, String email, String password, String repeatPassword, Role userRole) {
        if (username == null || username.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        if (this.userRepository.findByEmail(email).isPresent())
            throw new EmailAlreadyExistsException(email);
        User user = new User(username, email, passwordEncoder.encode(password), userRole);
        return userRepository.save(user);
    }

    @Override
    public List<Integer> calculate1RMList(int weight, int reps) {
        List<Integer> forEveryRep = new ArrayList<>();
//        ( 100*weight )/( 48.8 + (53.8 * Math.exp(-0.075*reps)) )
        for (int i = 0; i < 10; i++) {
            forEveryRep.add(-1 + (int) Math.floor((100 * weight) / (48.8 + (53.8 * Math.exp(-0.075 * (reps - i))))));
        }
        return forEveryRep;
    }

    @Override
    public int calculate1RM(int weight, int reps) {
        return (int) Math.floor((100 * weight) / (48.8 + (53.8 * Math.exp(-0.075 * reps))));
    }

    @Override
    public double calculateWILK(int bodyweight, int weightlifted, int gender) {
        double a = 594.31747775582,
                b = -27.23842536447,
                c = 0.82112226871,
                d = -0.00930733913,
                e = 4.731582E-05,
                f = -9.054E-08;
        if (gender == 0) { // if male
            a = -216.0475144;
            b = 16.2606339;
            c = -0.002388645;
            d = -0.00113732;
            e = 7.01863E-06;
            f = -1.291E-08;
        }
        return weightlifted * 500 / (a +
                b * bodyweight +
                c * Math.pow(bodyweight, 2) +
                d * Math.pow(bodyweight, 3) +
                e * Math.pow(bodyweight, 4) +
                f * Math.pow(bodyweight, 5));
    }

    @Override
    public int calculateTDEE(int bodyweight, int bodyfat, int age, int height, int gender, double activitylevel) {
        int result = (int) ((10 * bodyweight + 6.25 * height - 5 * age - 161) * activitylevel);
        if (gender == 0) { // if male
            result = (int) ((10 * bodyweight + 6.25 * height - 5 * age + 5) * activitylevel);
        }
        return result;
    }

    @Override
    public int calculateTDEE_WithBodyFat(int bodyweight, int bodyfat) {
        return (int) (370 + 21.6 * (1 - bodyfat / 100) * bodyweight);
    }

    @Override
    public List<User> addNewFriend(User user, String friend) {
        User friendToAdd = userRepository.findByUsername(friend).orElse(null);
        if (friendToAdd == null || user.getUsername().equals(friend)) return null;
        if (user.getFriends().contains(friendToAdd)) return null;
        user.getFriends().add(friendToAdd);
        friendToAdd.getFriends().add(user);
        userRepository.save(user);
        userRepository.save(friendToAdd);
        return user.getFriends();
    }

    @Override
    public void setUserSettings(String currUserName, String units, String sex, int bw, int age) {
        User u = userRepository.findByUsername(currUserName).orElseThrow();

        u.setUnits(Units.valueOf(units));
        u.setSex(Sex.valueOf(sex));
        u.setBodyweight(bw);
        u.setUserAge(age);
        userRepository.save(u);
    }


    @Override
    public void addLoggedLifts(String userId, LoggedLifts loggedLifts) {
        User u = userRepository.getById(userId);
        u.getLoggedLifts().add(loggedLifts);
        userRepository.save(u);
    }

    @Override
    public LoggedLifts findMostRecentLoggedLift(User user) {
        if (getLoggedLifts(user) == null) return null;
        return getLoggedLifts(user).get(getLoggedLifts(user).size() - 1);
    }

    @Override
    public List<LoggedLifts> getLoggedLifts(User user) {
        return user.getLoggedLifts();
    }

    @Override
    public Map<String, Double> calculateStrenghtStandard(List<String> categoryName, List<String> exName, List<Integer> weight, List<Integer> reps, int bodyweight, String gender) {

        Map<String, Double> score = new HashMap<>();
        // for men ===>> za Score po kategorija se zima pogolemiot od 2te
        if (gender.equals("Male")) {
            for (int i = 0; i < weight.size(); i++) {
                System.out.println(weight.get(i) + "  reps:"+ reps.get(i));
                int forOneRep = calculate1RM(weight.get(i), reps.get(i));

                if (exName.get(i).equals("Front Squat"))
                    score.put("SQUAT", forOneRep / 1.70); // for Front Squat
                if (exName.get(i).equals("Back Squat") && score.get("SQUAT") < forOneRep)
                    score.replace("SQUAT", forOneRep / 2.127); // for back squat

                if (exName.get(i).equals("Deadlift") || exName.get(i).equals("Sumo Deadlift"))
                    score.put("FLOOR PULL", forOneRep / 2.4458); // for DL and sumo
                if (exName.get(i).equals("Power Clean") && score.get("FLOOR PULL") < forOneRep)
                    score.replace("FLOOR PULL", forOneRep / 2.127); // for Power Clean

                if (exName.get(i).equals("Bench Press"))
                    score.put("HORIZONTAL PRESS", forOneRep / 1.589); // for Bench Press

                if (exName.get(i).equals("Snatch Press"))
                    score.put("VERTICAL PRESS", forOneRep / 0.827); // for Snatch Press

                if (exName.get(i).equals("Pendlay Row"))
                    score.put("PULL-UP / ROW", forOneRep / 1.298); // specifically for Pendlay Row
            }

        }
        else {
            for (int i = 0; i < exName.size(); i++) {
                int forOneRep = calculate1RM(weight.get(i), reps.get(i));




            }
        }

        return score;
    }

}

