package com.example.gymcalculator_2.model;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@Table
public class Exercises { // ova ke go punime vo databaza

    @Id
    private Long id;

    @ElementCollection
    private List<String> ListOf_ExToChooseFrom = Arrays.asList("","","","");// todo tuka treba da se navedat site mozni exercises

    public Exercises() {
    }
}
