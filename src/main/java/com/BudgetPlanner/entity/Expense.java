package com.BudgetPlanner.entity;


import jakarta.persistence.*;
import lombok.*;

import java.text.DecimalFormat;
import java.time.LocalDate;

@Entity

@Data
@Table(name="expense")
public class Expense {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long Id;
   private String category;
   @ManyToOne(fetch = FetchType.EAGER) // Ensure eager fetching to load budget
   @JoinColumn(name = "Budget_id", nullable = false)
   private Budget budget;
   private Double amount;
   private LocalDate DOB;
   private  String description;

   public void setId(Long id) {
      this.Id = id;
   }

   public Budget getBudget() {
      return budget;
   }

   public Double getAmount() {
      return amount;
   }


   public Long getId() {
      return Id;
   }
}
