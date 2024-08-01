package com.BudgetPlanner.repository;

import com.BudgetPlanner.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRopository  extends JpaRepository<Expense,Long> {
}
