package com.example.gymcalculator_2.model.Exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email) {
        super(String.format("%s already exists",email));
    }
}
