package com.BudgetPlanner.repository;

import com.BudgetPlanner.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
