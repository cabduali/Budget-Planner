package com.BudgetPlanner.service;

import com.BudgetPlanner.entity.Budget;
import com.BudgetPlanner.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Budgetservice {
    private  final BudgetRepository budgetRepository;

    public Budgetservice(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public  void  insertBudget(Budget budget){
        budgetRepository.save(budget);
    }

    public  void deleteBudget(Long id){
        budgetRepository.deleteById(id);
    }
    public  Budget  getBudgetAlById(Long id){
        return  budgetRepository.findById(id).orElse(null);
    }

    public List<Budget> getalBudgetalltable(){
        return  budgetRepository.findAll();
    }

}
