package finki.ukim.mk.gymcalculator.repository;

import finki.ukim.mk.gymcalculator.model.LoggedLifts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggedLiftsRepository extends JpaRepository<LoggedLifts,String> {
}
