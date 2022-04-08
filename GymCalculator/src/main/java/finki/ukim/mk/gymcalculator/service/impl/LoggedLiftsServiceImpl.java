package finki.ukim.mk.gymcalculator.service.impl;

import finki.ukim.mk.gymcalculator.repository.LoggedLiftsRepository;
import finki.ukim.mk.gymcalculator.service.LoggedLiftsService;
import org.springframework.stereotype.Service;

@Service
public class LoggedLiftsServiceImpl implements LoggedLiftsService {
    private final LoggedLiftsRepository loggedLiftsRepository;

    public LoggedLiftsServiceImpl(LoggedLiftsRepository loggedLiftsRepository) {
        this.loggedLiftsRepository = loggedLiftsRepository;
    }
}
