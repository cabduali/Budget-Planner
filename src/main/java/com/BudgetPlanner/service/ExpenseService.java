package com.BudgetPlanner.service;

import com.BudgetPlanner.entity.Budget;
import com.BudgetPlanner.entity.Expense;
import com.BudgetPlanner.repository.BudgetRepository;
import com.BudgetPlanner.repository.ExpenseRopository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRopository expenseRopository;
    private final BudgetRepository budgetRepository;

    @Autowired
    public ExpenseService(ExpenseRopository expenseRopository, BudgetRepository budgetRepository) {
        this.expenseRopository = expenseRopository;
        this.budgetRepository = budgetRepository;
    }

    public Boolean insertExpense(Expense expense) {
        Budget budget = expense.getBudget();
        Double updateTotalAmount = budget.getTotalAmount() - expense.getAmount();
        if (updateTotalAmount < 0) {
            return false; // Not enough budget
        }
        budget.setTotalAmount(updateTotalAmount);
        budgetRepository.save(budget);
        expenseRopository.save(expense);
        return true;
    }

    public boolean updateExpense(Expense expense) {
        // Find the existing expense
        Expense existingExpense = expenseRopository.findById(expense.getId()).orElse(null);
        if (existingExpense == null) {
            return false; // Expense does not exist
        }

        Budget budget = expense.getBudget();
        Double oldAmountExpense = existingExpense.getAmount();
        Double newAmountExpense = expense.getAmount();
        Double totalAmountChange = oldAmountExpense - newAmountExpense;
        Double updateAmount = budget.getTotalAmount() + totalAmountChange;

        if (updateAmount < 0) {
            return false; // Not enough budget
        }

        budget.setTotalAmount(updateAmount);
        budgetRepository.save(budget);
        expenseRopository.save(expense);
        return true;
    }

    public void deleteExpense(Long id) {
        expenseRopository.deleteById(id);
    }

    public Expense getExpenseById(Long id) {
        return expenseRopository.findById(id).orElse(null);
    }

    public List<Expense> getallExpense() {
        return expenseRopository.findAll();
    }


}
