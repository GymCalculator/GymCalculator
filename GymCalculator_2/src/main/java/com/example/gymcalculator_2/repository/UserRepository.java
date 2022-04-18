package com.example.gymcalculator_2.repository;


import com.example.gymcalculator_2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

//    public List<User> findUsersByUsername(List<String> username); // one
//    public List<User> findAllByUsername (List<String> username); // or the other -> idk both might be OK

}
