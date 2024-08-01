package com.BudgetPlanner.controller;

import com.BudgetPlanner.entity.Budget;
import com.BudgetPlanner.entity.Expense;
import com.BudgetPlanner.entity.Income;
import com.BudgetPlanner.service.Budgetservice;
import com.BudgetPlanner.service.ExpenseService;
import com.BudgetPlanner.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class transactionController {
   private  final  Budgetservice budgetservice;
   private  final ExpenseService expenseService;
   private  final IncomeService incomeService;

@Autowired
    public transactionController(Budgetservice budgetservice, ExpenseService expenseService, IncomeService incomeService) {
        this.budgetservice = budgetservice;
    this.expenseService = expenseService;
    this.incomeService = incomeService;
}


    @GetMapping("/showtransctiion")
    public String showtable(Model model){
        List<Budget> Budg=budgetservice.getalBudgetalltable();
        model.addAttribute("budgets",Budg);
        List<Expense> List=expenseService.getallExpense();
//        List<Income> inc=incomeService.getaincomeTable();
        model.addAttribute("expenses",List);
        return "Transaction";
    }






}
