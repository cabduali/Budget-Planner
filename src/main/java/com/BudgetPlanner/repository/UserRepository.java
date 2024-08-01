package com.BudgetPlanner.repository;

import com.BudgetPlanner.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findByUserName(String username);
}
