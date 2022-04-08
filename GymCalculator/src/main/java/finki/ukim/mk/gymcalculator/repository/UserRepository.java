package finki.ukim.mk.gymcalculator.repository;

import finki.ukim.mk.gymcalculator.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

//    public List<User> findUsersByUsername(List<String> username); // one
//    public List<User> findAllByUsername (List<String> username); // or the other -> idk both might be OK

}
