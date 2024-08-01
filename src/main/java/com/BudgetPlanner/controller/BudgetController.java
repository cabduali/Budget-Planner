package com.BudgetPlanner.controller;

import com.BudgetPlanner.entity.Budget;
import com.BudgetPlanner.entity.Client;
import com.BudgetPlanner.entity.Expense;
import com.BudgetPlanner.service.Budgetservice;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class BudgetController {
    private  final Budgetservice budgetservice;

@Autowired
    public BudgetController(Budgetservice budgetservice) {
        this.budgetservice = budgetservice;
    }

//    @GetMapping("/showBudget")
//    public  String showbudget(Model model){
//        model.addAttribute("budget", new Budget());
//        return  "BudgetTable";
//    }
@GetMapping("/AddBudget")
public String addExpense(Model model){
    model.addAttribute("budgets", new Budget());
    return "FormBudget";
}


//    @PostMapping("/submitAdd")
//    public String submitAdd(@ModelAttribute Expense expense, HttpSession session){
//        Client client = (Client) session.getAttribute("client");
//         expenseService.insertExpense(expense);
//        return "redirect:/list";
//    }

    //Galinta database xogta



    @PostMapping("/AddBudget")
    public  String saveBudget(@ModelAttribute Budget budget){
        budgetservice.insertBudget(budget);
        return  "FormBudget";
    }

    @GetMapping("/showBudget")
    public String showtable(Model model){
        List<Budget> Budg=budgetservice.getalBudgetalltable();
        model.addAttribute("budgets",Budg);
        return "BudgetTable";
    }


    @RequestMapping("/deleteBudget/{id}")
    public  String deleteBudget(@PathVariable Long id){
        budgetservice.deleteBudget(id);
        return "redirect:/showBudget";
    }

    @RequestMapping("/updateBudget/{id}")
    public  String updatebudget(@PathVariable Long id, Model model){
        Budget bgg=budgetservice.getBudgetAlById(id);
        model.addAttribute("budgetss",bgg);
        return "Updatebudgets";

    }


}
