package com.BudgetPlanner.controller;


import com.BudgetPlanner.entity.Client;
import com.BudgetPlanner.entity.Expense;
import com.BudgetPlanner.repository.BudgetRepository;
import com.BudgetPlanner.repository.ExpenseRopository;
import com.BudgetPlanner.service.ExpenseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ExpenseController {
    @Autowired
    private final ExpenseService expenseService;

    @Autowired
    private final BudgetRepository budgetRepository;

    @Autowired
    private final ExpenseRopository expenseRopository;

    public ExpenseController(ExpenseService expenseService, BudgetRepository budgetRepository, ExpenseRopository expenseRopository) {
        this.expenseService = expenseService;
        this.budgetRepository = budgetRepository;
        this.expenseRopository = expenseRopository;
    }

    @GetMapping("/")
    public String landingPage(HttpSession session, Model model) {
        Client client = (Client) session.getAttribute("client");
        model.addAttribute ("sessionClient", client);
        return "landing-page";
    }

    @GetMapping("/showAdd")
    public String addExpense(Model model) {
        model.addAttribute("expense", new Expense());
        model.addAttribute("budgets", budgetRepository.findAll()); // Ensure budgets are available
        return "FormExpense";
    }

    @PostMapping("/submitAdd")
    public String submitAdd(@ModelAttribute Expense expense, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("budgets", budgetRepository.findAll());
            return "FormExpense";
        }

        boolean isSaved = expenseService.insertExpense(expense);
        if (!isSaved) {
            result.rejectValue("amount", "error.expense", "Insufficient budget amount.");
            model.addAttribute("budgets", budgetRepository.findAll());
            return "FormExpense";
        }
        return "redirect:/list";
    }

//    @GetMapping("/list")
//    public String getAll(Model model) {
//        List<Expense> list = expenseService.getallExpense();
//        model.addAttribute("expenses", list);
//        return "TableViewExpense";
//    }
@GetMapping("/list")
public String Getall(Model model){
    List<Expense> List=expenseService.getallExpense();
    model.addAttribute("expenses",List);
    return "TableViewExpense";
}

    @RequestMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/list";
    }

    @RequestMapping("/update/{id}")
    public String updateExpense(@PathVariable Long id, Model model) {
        Expense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        model.addAttribute("budgets", budgetRepository.findAll());
        return "FormExpense";
    }

    @PostMapping("/update/{id}")
    public String updateExpenses(@PathVariable Long id, @ModelAttribute Expense expense, BindingResult result, Model model) {
        expense.setId(id);

        boolean isUpdated = expenseService.updateExpense(expense);
        if (!isUpdated) {
            result.rejectValue("amount", "error.expense", "Insufficient budget amount.");
            model.addAttribute("budgets", budgetRepository.findAll());
            return "FormExpense";
        }
        return "redirect:/list";
    }
}
