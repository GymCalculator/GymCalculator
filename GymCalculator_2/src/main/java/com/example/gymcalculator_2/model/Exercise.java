package com.example.gymcalculator_2.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "exercise")
public class Exercise {
    @Id
    private Long id;

    private String exName;

    private String exGroup;

    public Exercise() {
    }

    public Exercise(Long id, String exName, String exGroup) {
        this.id = id;
        this.exName = exName;
        this.exGroup = exGroup;
    }
}
