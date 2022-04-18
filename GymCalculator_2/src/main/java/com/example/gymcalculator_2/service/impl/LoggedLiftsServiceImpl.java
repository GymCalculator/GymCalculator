package com.example.gymcalculator_2.service.impl;



import com.example.gymcalculator_2.repository.LoggedLiftsRepository;
import com.example.gymcalculator_2.service.LoggedLiftsService;
import org.springframework.stereotype.Service;

@Service
public class LoggedLiftsServiceImpl implements LoggedLiftsService {
    private final LoggedLiftsRepository loggedLiftsRepository;

    public LoggedLiftsServiceImpl(LoggedLiftsRepository loggedLiftsRepository) {
        this.loggedLiftsRepository = loggedLiftsRepository;
    }
}
