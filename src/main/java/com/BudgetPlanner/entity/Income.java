package com.BudgetPlanner.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDate;
@Entity
@Data
@Table(name="incometable")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private  int id;
    private String category;
    private int amount;
    private LocalDate DOB;
    private  String descriptiom;
}
