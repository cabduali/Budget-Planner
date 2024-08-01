package com.BudgetPlanner.repository;

import com.BudgetPlanner.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget,Long> {
}
